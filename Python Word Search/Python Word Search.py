def is_valid_file_name():
    '''()->str or None'''
    file_name = None
    try:
        file_name=input("Enter the name of the file: ").strip()
        f=open(file_name)
        f.close()
    except FileNotFoundError:
        print("There is no file with that name. Try again.")
        file_name=None
    return file_name 

def get_file_name():
    file_name=None
    while file_name==None:
        file_name=is_valid_file_name()
    return file_name

def clean_word(word):
    '''(str)->str
    Returns a new string which is lowercase version of the given word
    with special characters and digits removed

    The returned word should not have any of the following characters:
    ! . ? : , ' " - _ \ ( ) [ ] { } % 0 1 2 3 4 5 6 7 8 9 tab character and new-line character

    >>> clean_word("co-operate.")
    'cooperate'
    >>> clean_word("Anti-viral drug remdesivir has little to no effect on Covid patients' chances of survival, a study from the World Health Organization (WHO) has found.")
    'antiviral drug remdesivir has little to no effect on covid patients chances of survival a study from the world health organization who has found'
    >>> clean_word("1982")
    ''
    >>> clean_word("born_y1982_m08\n")
    'bornym'

    '''
    
    cleaned_word = ''
    filtered = '!.?:,\'"-_\()[]{}%0123456789'
    for letter in sentence:
        if letter not in filtered:
            cleaned_word += letter
    return cleaned_word.lower()

def test_letters(w1, w2):
    '''(str,str, list of str)->bool
    Given two strings w1 and w2 representing two words,
    the function returns True if w1 and w2 have exactlly the same letters,
    and False otherwise

    >>> test_letters("listen", "enlist")
    True
    >>> test_letters("eekn", "knee")
    True
    >>> test_letters("teen", "need")
    False
    '''

    a = sorted(w1.lower())
    b = sorted(w2.lower())
    if a == b:
        return True
    else:
        return False
    
def create_clean_sorted_nodupicates_list(s):
    '''(str)->list of str
    Given a string s representing a text, the function returns the list of words with the following properties:
    - each word in the list is cleaned-up (no special characters nor numbers)
    - there are no duplicated words in the list, and
    - the list is sorted lexicographicaly (you can use python's .sort() list method or sorted() function.)

    This function must call clean_word() function.

    You may find it helpful to first call s.split() to get a list version of s split on white space.
    
    >>> create_clean_sorted_nodupicates_list('able "acre bale beyond" binary boat brainy care cat cater crate lawn\nlist race react cat sheet silt slit trace boat cat crate.\n')
    ['able', 'acre', 'bale', 'beyond', 'binary', 'boat', 'brainy', 'care', 'cat', 'cater', 'crate', 'lawn', 'list', 'race', 'react', 'sheet', 'silt', 'slit', 'trace']

    >>> create_clean_sorted_nodupicates_list('Across Europe, infection rates are rising, with Russia reporting a record 14,321 daily cases on Wednesday and a further 239 deaths.')
    ['', 'a', 'across', 'and', 'are', 'cases', 'daily', 'deaths', 'europe', 'further', 'infection', 'on', 'rates', 'record', 'reporting', 'rising', 'russia', 'wednesday', 'with']
    '''
    
    s = s.lower()
    specialchar = '''!.?:,'"-_\()[]{}%1234567890\n\t''' # letters to check specials and numbers
    new_s = ""
    
    # remove special characters and numbers
    for char in s:
        if char not in specialchar:
            new_s = new_s + char
    
    # convert string into list using split()
    list2 = new_s.split(' ')
    
    output = [] # make empty list
    [output.append(z) for z in list2 if z not in output] # remove duplicates by using new list having single value
    
    return sorted(output) # print sorted list with no duplicates
    
def word_anagrams(word, wordbook):
    '''(str, list of str) -> list of str
    - a string (representing a word)
    - wordbook is a list of words (with no words duplicated)

    This function must call test_letters() function.
    The function returns a (lexicographicaly sorted) list of anagrams of the given word in wordbook
    

    >>> word_anagrams("listen", wordbook)
    ['silent', 'enlist', 'tinsel']
    >>> word_anagrams("race", wordbook)
    ['care', 'acre']
    >>> word_anagrams("care", wordbook)
    ['acre', 'race']
    >>> word_anagrams("year", wordbook)
    []
    >>> word_anagrams("ear", wordbook)
    ['are', 'era']
    '''
    
    anagramWords = [] # to store the anagram words 
    
    for string in wordbook:
        isit = test_letters(string,word)
        if isit and word!=string:
            anagramWords.append(string)
            
    return sorted(anagramWords)


def count_anagrams(l, wordbook):
    '''(list of str, list of str) -> list of int

    - l is a list of words (with no words duplicated)
    - wordbook is another list of words (with no words duplicated)

    The function returns a list of integers where i-th integer in the list
    represents the number of anagrams in wordbook of the i-th word in l.
    
    Whenever a word in l is the same as a word in wordbook, that is not counted.

    >>> count_anagrams(["listen","care", "item", "year", "race", "ear"], wordbook)
    [3, 2, 3, 0, 2, 2]

    The above means that "listen" has 3 anagrams in wordbook, that "care" has 2 anagrams in wordbook ...
    Note that wordbook has "care", "race" and "acre" which are all anagrams of each other.
    When we count anagrams of "care" we count "race" and "acre" but not "care" itself.
    '''

    list1 = []      # empty list for the counting function
    for word2 in l:     # first loop for function
        x = 0
        for word3 in wordbook:      # loop for wordbook
            if sorted(word2) == sorted(word3):
                x = x + 1
        list1.append(x)
    return list1



def k_anagram(l, anagcount, k):
    '''(list of str, list of int, int) -> list of str

    - l is a list of words (with no words duplicated)
    - anagcount is a list of integers where i-th integer in the list
    represents the number of anagrams in wordbook of the i-th word in l.

    The function returns a  (lexicographicaly sorted) list of all the words
    in l that have exactlly k anagrams (in wordbook as recorded in anagcount)

    k_anagram(["listen","care", "item", "year", "race", "ear"], [3, 2, 3, 0, 2, 2], 2)
    ['care', 'race', 'ear']
    '''
    
    newlist = []
    for i in range(0,len(l)):
        if(anagcount[i] == k):
            newlist.append(l[i])
    newlist.sort()
    return newlist


def max_anagram(l, anagcount):
    '''(list of str, list of int) -> list of str
    - l is a list of words (with no words duplicated)
    - anagcount is a list of integers where i-th integer in the list
    represents the number of anagrams in wordbook of the i-th word in l.

    The function returns a (lexicographicaly sorted) list of all the words
    in l with maximum number of anagrams (in wordbook as recorded in anagcount)
    
    >>> max_anagram(["listen","care", "item", "year", "race", "ear"], [3, 2, 3, 0, 2, 2])
    ['listen', 'item']
    '''
    maxVal = max(anagcount)
    maxlist = []
    for i in range(0,len(l)):
        if(anagcount[i] == maxVal):
            maxlist.append(l[i])
    maxlist.sort()
    return maxlist

def zero_anagram(l, anagcount):
    '''(list of str, list of int) -> list of str
    - l is a list of words (with no words duplicated)
    - anagcount is a list of integers where i-th integer in the list
    represents the number of anagrams in wordbook of the i-th word in l.

    The function returns a (lexicographicaly sorted) list of all the words
    in l with no anagrams
    (in wordbook as recorded in anagcount)
    
    >>> zero_anagram(["listen","care", "item", "year", "race", "ear"], [3, 2, 3, 0, 2, 2])
    ['year']
    '''

    zerolist = []
    for i in range(0,len(l)):
        if(anagcount[i] == 0):
            zerolist.append(l[i])
    zerolist.sort()
    return zerolist
            



                
    
##############################
# main
##############################
wordbook=open("english_wordbook.txt").read().lower().split()
list(set(wordbook)).sort()

print("Would you like to:")
print("1. Analize anagrams in a text -- given in a file")
print("2. Get small help for Scrabble game")
print("Enter any character other than 1 or 2 to exit: ")
choice=input()

# when asking for k from the user you may assume that they will enter non-negative integer
if choice=='1':
    file_name=get_file_name()
    rawtx = open(file_name).read()
    l=create_clean_sorted_nodupicates_list(rawtx)
    anagcount = count_anagrams(l,wordbook)

    print("\nOf all the words in your file, the following words have the most anagrams:")

    # YOUR CODE GOES HERE    
    max_anagram(l, anagcount)
    for i in max_anagram(l, anagcount):
        print(word_anagrams(i, wordbook))
    zero_anagram(l, anagcount)
    k = input('Enter an integer for k: ')
    print(k_anagram(l, anagcount, k))

    
elif choice=='2':

    #YOUR CODE GOES HERE
    print(input('Enter the letters that you have, one after another with no space:\n '))
    pass
                       
                      
else:
    print("Good bye")


