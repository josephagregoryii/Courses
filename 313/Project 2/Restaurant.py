import sys
from Queue import Queue

def driver():
    q1 = Queue()    #queue to hold energy gain
    q2 = Queue()    #queue to store positive energy
    total = 0       #energy total
    position = 0    #restaurant position
    with open(sys.argv[1]) as f:
        n = int(f.readline().strip()) #number of lines to read
        for _ in range(n):
            in_data = f.readline().strip().split(" ")   #data for queue
            energy = int(in_data[0]) - int(in_data[1])  #energy of energy received minus energy used
            q1.enqueue(energy)   #store the energy, positive or negative
            total += energy     #add the total energy by the current energy
            while total < 0:    #if total is less than 0,
                energy = q1.dequeue()   #remove the energy
                total -= energy     #subtract it from the total; adds back the energy that was taken off
                q2.enqueue(energy)  #adds positive energy into q2
                position += 1   #increment restaurant position
        while not q2.is_empty():
            energy = q2.dequeue() #remove the energy
            total += energy #add energy to total
            q1.enqueue(energy)  #remove it from q1
            while total < 0:
                energy = q1.dequeue()
                total -= energy
                q2.enqueue(energy)
                start += 1
    print(position)

if __name__ == "__main__":
    driver()