
import sys, copy
from LinkedList import LinkedList

class Underflow(Exception):
    pass

class Queue:

    def __init__(self):
        self.ll = LinkedList()

    def enqueue(self, x):
        self.ll.insert_Q(x)

    def dequeue(self):
        if not self.is_empty():
            data = self.ll.sntl.next.data
            self.ll.delete(self.ll.sntl.next)
            return data
        raise Underflow("QueueError")

    def is_empty(self):
        return self.ll.size == 0

def print_queue(q):
    if not q.is_empty():
        new_Queue = copy.deepcopy(q)
        v = []
        for i in range(len(new_Queue.ll)):
            v.append(str(new_Queue.dequeue()))
        return " ".join(v)

    return "Empty"

def driver():
    q = Queue()
    with open(sys.argv[1]) as f:
        n = int(f.readline().strip())
        for _ in range(n):
            in_data = f.readline().strip().split()
            action, value_option = in_data[0], in_data[1:]
            if action == "enqueue":
                value = int(value_option[0])
                q.enqueue(value)
            elif action == "dequeue":
                try:
                    print(q.dequeue())
                except Underflow:
                    print("QueueError")
            elif action == "print":
                print(print_queue(q))

if __name__ == "__main__":
    driver()
