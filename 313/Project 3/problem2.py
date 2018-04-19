from minheap import MinHeap
from maxheap import MaxHeap
import sys

def driver():
    min = MinHeap()
    max = MaxHeap()
    values = []
    with open(sys.argv[1]) as f:
        n = int(f.readline().strip())
        for _ in range(n):
            in_data = f.readline().strip().split()
            integer = in_data[0]
            values.append(int(integer))

    for val in values:
        if len(min) == 0:
            min.insert(val)
            print(val)
        elif len(max) == 0:
            max.insert(val)
            if val > min.array[1]:
                min.array[1],max.array[1] = max.array[1], min.array[1]
            else:
                min.array[1], max.array[1] = max.array[1], min.array[1]
            print((max.array[1]+(val))/2)
        else:
            length = len(min) - len(max)
            if length == 0:
                if val > min.array[1]:
                    min.insert(val)
                else:
                    max.insert(val)
            elif length == 1:
                if val >= max.array[1]:
                    min.insert(val)
                    x = min.remove()
                    max.insert(x)
                else:
                    max.insert(val)
            elif length == -1:
                if val <= max.array[1]:
                    min.insert(val)
                else:
                    max.insert(val)
                    x = max.remove()
                    min.insert(x)
            length = len(min) - len(max)
            if length == 0:
                if (float((max.array[1]+min.array[1])/2).is_integer()):
                    print(int((max.array[1]+min.array[1])/2))
                    max.array[1], min.array[1] = min.array[1], max.array[1]
                else:
                    print(float((max.array[1] + min.array[1]) / 2))
                    max.array[1],min.array[1] = min.array[1],max.array[1]
            elif length == 1:
                if min.array[1] > max.array[1]:
                    print(min.array[1])
                else:
                    print(max.array[1])
            elif length == -1:
                print(max.array[1])




if __name__ == "__main__":
    driver()