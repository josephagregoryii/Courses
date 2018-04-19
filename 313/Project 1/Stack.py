
import sys, copy
from LinkedList import LinkedList

class Underflow(Exception):
    pass

class Stack:

    def __init__(self):
        self.ll = LinkedList()
        return

    def push(self, x):
        self.ll.insert(x)
        return

    def pop(self):
        if self.is_empty():
            raise Underflow("StackError")
        else:
            data = self.ll.sntl.next.data
            self.ll.delete(self.ll.sntl.next)

        return data

    def is_empty(self):
        return self.ll.size == 0

def print_stack(s):
    if s.is_empty():
        return "Empty"
    else:
        new_Stack = copy.deepcopy(s)
        v = []
        for i in range(len(new_Stack.ll)):
            data = new_Stack.pop()
            v.append(str(data))
        return " ".join(v)

def driver():
    s = Stack()
    with open(sys.argv[1]) as f:
        n = int(f.readline().strip())
        for _ in range(n):
            in_data = f.readline().strip().split()
            action, value_option = in_data[0], in_data[1:]
            if action == "push":
                value = int(value_option[0])
                s.push(value)
            elif action == "pop":
                try:
                    print(s.pop())
                except Underflow:
                    print("StackError")
            elif action == "print":
                print(print_stack(s))

if __name__ == "__main__":
    driver()
