package com.thealgorithms.misc;

import com.thealgorithms.datastructures.lists.SinglyLinkedList;
import com.thealgorithms.datastructures.lists.SinglyLinkedList.Node;

import java.util.Stack;
/**
 * A simple way of knowing if a singly linked list is palindrome is to push all
 * the values into a Stack and then compare the list to popped vales from the
 * Stack.
 *
 * See more:
 * https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 */
public final class PalindromeSinglyLinkedList {

    private PalindromeSinglyLinkedList() {
    }

    public static boolean isPalindrome(final SinglyLinkedList linkedList) {
        Stack<Integer> linkedListValues = new Stack<>();

        for (final var x : linkedList) {
            linkedListValues.push(x);
        }

        for (final var x : linkedList) {
            if (x != linkedListValues.pop()) {
                return false;
            }
        }

        return true;
    }
    
    // Optimised approach in O(n) time and O(1) space complexity
    public static boolean isPalindromeOptimized(final SinglyLinkedList linkedList) {
        SinglyLinkedList.Node head = linkedList.getHead(); 
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle of the linked list using the slow and fast pointers
        SinglyLinkedList.Node slow = head; // Slow pointer
        SinglyLinkedList.Node fast = head; // Fast pointer

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer by 1
            fast = fast.next.next; // Move fast pointer by 2
        }

        // Step 2: Reverse the second half of the list
        SinglyLinkedList.Node secondHalfStart = reverseList(slow); // Reverse from slow

        // Step 3: Compare the first half and the reversed second half
        SinglyLinkedList.Node left = head; // Left pointer
        SinglyLinkedList.Node right = secondHalfStart; // Right pointer

        while (right != null) {
            if (left.value != right.value) {
                return false; // If values mismatch, the list is not a palindrome
            }
            left = left.next; // Move left pointer to next node
            right = right.next; // Move right pointer to next node
        }

        return true; 
    }

    // Helper function to reverse the linked list starting from a given node
    private static SinglyLinkedList.Node reverseList(SinglyLinkedList.Node head) {  
        SinglyLinkedList.Node prev = null;  
        Node current = head;  

        while (current != null) {
            Node next = current.next; // Access next directly
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev; // 'prev' will be the new head of the reversed list
    }
}
