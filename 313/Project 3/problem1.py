from binaryheap import BinaryHeap
import sys

def driver():
    with open(sys.argv[1]) as f:
        Abh = BinaryHeap()
        Bbh = BinaryHeap()
        count_a = 1
        count_b = 1
        n = int(f.readline().strip())
        for _ in range(n):
            in_data = f.readline().strip().split()
            if in_data[1] == "A":
                tup = (count_a,in_data[0], in_data[1], in_data[2])
                Abh.insert(tup)
                count_a += 1
            elif in_data[1] == "B":
                tup = (count_b,in_data[0], in_data[1], in_data[2])
                Bbh.insert(tup)
                count_b +=1
        Abh.print_elem()
        Bbh.print_elem()

if __name__ == '__main__':
    driver()