import sys


class Underflow(Exception):
    def __init__(self, data=None):
        super().__init__(data)

class MinHeap:

    def __init__(self):
        self.heap = [None]
        self.currentSize = 0

    def decrease_key(self, i):
        while i // 2 > 0:
            if self.heap[i] < self.heap[i // 2]:
                temp = self.heap[i // 2]
                self.heap[i // 2] = self.heap[i]
                self.heap[i] = temp
            i = i // 2

    def insert(self,x):
        self.heap.append(x)
        self.currentSize = self.currentSize + 1
        self.decrease_key(self.currentSize)

    def heapDown(self, i):
        while (i * 2) <= self.currentSize:
            min = self.minChild(i)
            if self.heap[i] > self.heap[min]:
                tmp = self.heap[i]
                self.heap[i] = self.heap[min]
                self.heap[min] = tmp
            i = min

    def minChild(self, i):
        if i * 2 + 1 > self.currentSize:
            return i * 2
        else:
            if self.heap[i * 2] < self.heap[i * 2 + 1]:
                return i * 2
            else:
                return i * 2 + 1

    def remove(self):
        if self.is_empty():
            raise Underflow
        else:
            result = self.heap[1]
            self.heap[1] = self.heap[self.currentSize]
            self.currentSize = self.currentSize - 1
            self.heap.pop()
            self.heapDown(1)
            return result

    def look(self):
        if self.is_empty():
            raise Underflow("HeapError")
        else:
            return self.heap[1]

    def size(self):
        return self.currentSize

    def is_empty(self):
        if self.currentSize == 0:
            return True
        else:
            return False

    def to_string(self):
        if self.is_empty():
            return "Empty"
        else:
            new = self.heap[1 : self.currentSize + 1]
            v = []
            for i in new:
                v.append(str(i))
            return " ".join(v)


def driver():
    min_heap = MinHeap()
    with open(sys.argv[1]) as f:
        n = int(f.readline().strip())
        for _ in range(n):
            in_data = f.readline().strip().split(" ")
            action, value_option = in_data[0], in_data[1:]

            if action == "insert":
                value = int(value_option[0])
                min_heap.insert(int(value))

            elif action == "remove":
                try:
                    print(min_heap.remove())
                except Underflow:
                    print("HeapError")

            elif action == "print":
                print(min_heap.to_string())

            elif action == "best":
                try:
                    print(min_heap.look())
                except Underflow:
                    print("HeapError")

            elif action == "size":
                print(min_heap.size())

if __name__ == "__main__":
    driver()