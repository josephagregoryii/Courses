from collections import defaultdict #used defaultdict so if they key has not been set yet, it will "default" a value
import sys

def collage(magazine, note, x, y):
    word_count = defaultdict(lambda:0)  #initialize default dict

    for iter in range(x):
        word1 = magazine[iter]
        word_count[word1] += 1

    for iter2 in range(y):
        word2 = note[iter2]
        word_count[word2] -= 1
        if word_count[word2] < 0:
            return False
    return True

def driver():
    with open(sys.argv[1]) as f:
        x,y = map(int, f.readline().strip().split(" ")) # x = length of mag, y = length of note
        magazine = f.readline().strip().split(" ")  #list of words in magazine
        note = f.readline().strip().split(" ")  #list of words in note
        result = collage(magazine,note,x,y) #get the return value from collage()
        if(result): #base case to check if there is a True result
            print("YES")
        else:
            print("NO")

if __name__ == "__main__":
    driver()