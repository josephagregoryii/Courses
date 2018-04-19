class Queue:
    class Underflow(Exception):
        def __init__(self, data=None):
            super().__init__(data)

    class Node:
        def __init__(self, data=None):
            self.data = data
            self.next = None

    def __init__(self):
        self.head = None
        self.tail = None

    def enqueue(self, x: "datum") -> None:
        n = self.Node(x)
        if self.head == None:
            self.head = n
        else:
            self.tail.next = n
        self.tail = n

    def dequeue(self) -> "datum":
        if self.head == None:
            raise Queue.Underflow("QueueError")
        n = self.head
        self.head = n.next
        if self.head == None:
            self.tail = None
        return n.data

    def is_empty(self) -> bool:
        if self.head == None:
            return True
        else:
            return False

