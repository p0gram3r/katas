from collections import OrderedDict

def convert_to_roman(number):
    number_mapping = OrderedDict([
        (1000 , "M"),
        (900  , "CM"),
        (500  , "D"),
        (400  , "CD"),
        (100  , "C"),
        (50   , "L"),
        (40   , "XL"),
        (10   , "X"),
        (9    , "IX"),
        (5    , "V"),
        (4    , "IV"),
        (1    , "I")
    ])
    
    result = ""
    
    for key, letter in number_mapping.items():
        count = number // key 
        number = number % key
        result += letter * count
    
    return result
    


def convert_test(number):
    print number, convert_to_roman(number)
    
# test
convert_test(1)
convert_test(2)
convert_test(4)
convert_test(5)
convert_test(9)
convert_test(10)
convert_test(12)
convert_test(19)
convert_test(50)
convert_test(100)
convert_test(1984) #MCMLXXXIV