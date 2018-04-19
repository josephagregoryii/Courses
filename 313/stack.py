class Stack:
    class Underflow(Exception):
        def __init__(self, data=None):
            super().__init__(data)

    class Node:
        def __init__(self, data=None):
            self.data = data
            self.next = None

    def __init__(self):
        self.head = None

    def push(self, x: "datum") -> None:
        n = self.Node(x)
        n.next = self.head
        self.head = n

    def pop(self) -> "datum":
        if self.head == None:
            raise Stack.Underflow("Stack.pop() invoked on empty stack")
        n = self.head
        self.head = n.next
        return n.data

    def is_empty(self) -> bool:
        if self.head == None:
            return True
        else:
            return False
