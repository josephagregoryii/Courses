from Stack import Stack
import sys

def is_matching(bracket1, bracket2):
    if (bracket1 == '(' and bracket2 == ')'):
        return True
    elif (bracket1 == '{' and bracket2 == '}'):
        return True
    elif (bracket1 == '[' and bracket2 == ']'):
        return True
    elif (bracket1 == '<' and bracket2 == '>'):
        return True
    else:
        return False

def is_starting(bracket):
    if (bracket == '{' or bracket == '(' or bracket == '[' or bracket == '<'):
        return True
    else:
        return False

def is_ending(bracket):
    if (bracket == '}' or bracket == ')' or bracket == ']' or bracket == '>'):
        return True
    else:
        return False

def is_balanced(brackets):
    s = Stack()
    result = ''
    for i in range(len(brackets)):
        if(is_starting(brackets[i])):
            s.push(brackets[i])
        elif(is_ending(brackets[i])):
            if(s.is_empty()):
                return "NO"
            if not is_matching(s.pop(),brackets[i]):
                return "NO"

    if s.is_empty():
        result = "YES"
    else:
        result = "NO"
    return result

def driver():
    with open(sys.argv[1]) as f:
        n = int(f.readline().strip())
        for _ in range(n):
            in_data = f.readline().strip().split()
            result = is_balanced(in_data[0])
            print(result)

if __name__ == '__main__':
    driver()