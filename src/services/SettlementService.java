package services;

import models.*;

import java.util.*;

public class SettlementService {
    public List<Transaction> settle(Group group) {
        PriorityQueue<Node> maxHeapSender = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.getAmount() - n2.getAmount();
            }
        });
        PriorityQueue<Node> maxHeapRecipient = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.getAmount() - n2.getAmount();
            }
        });
        Map<UUID, Node> nodeMap = new HashMap<>();
        List<Expense> allExpensesInGroup = group.getExpenses();
        for (Expense expense : allExpensesInGroup) {
            for (Balance balance: expense.getBalances()) {
                User sender = balance.getFrom();
                User recipient = balance.getTo();
                int amount = balance.getAmount();
                Node senderNode = nodeMap.getOrDefault(sender.getId(), new Node(sender, 0));
                senderNode.setAmount(senderNode.getAmount()-amount);
                nodeMap.put(sender.getId(), senderNode);

                Node recipientNode = nodeMap.getOrDefault(recipient.getId(), new Node(recipient, 0));
                recipientNode.setAmount(recipientNode.getAmount()+amount);
                nodeMap.put(recipient.getId(), recipientNode);
            }
        }
        for (Map.Entry<UUID, Node> entry : nodeMap.entrySet()) {
            Node node = entry.getValue();
            if (node.getAmount() < 0) {
                node.setAmount(Math.abs(node.getAmount()));
                maxHeapSender.add(node);
            } else if (node.getAmount() > 0) {
                maxHeapRecipient.add(node);
            }
        }
        List<Transaction> transactions = new ArrayList<>();
        while (!maxHeapSender.isEmpty()) {
            Node sender = maxHeapSender.poll();
            Node recipient = maxHeapRecipient.poll();

            int amountTransferred = Math.min(recipient.getAmount(), sender.getAmount());
            transactions.add(new Transaction(recipient.getUser(), sender.getUser(), amountTransferred));
            sender.setAmount(sender.getAmount()-amountTransferred);
            recipient.setAmount(recipient.getAmount()-amountTransferred);
            if (sender.getAmount() > 0) {
                maxHeapSender.add(sender);
            }
            if (recipient.getAmount() > 0) {
                maxHeapRecipient.add(recipient);
            }
        }
        return transactions;
    }
}
