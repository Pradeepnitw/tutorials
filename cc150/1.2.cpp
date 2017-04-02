#include <iostream>
#include <string.h>
using namespace std;

// Implement a function void reverse(char* str) in C or C++ which
// reverses a null terminated string.

void reverse(char* str) {
    int i = 0;
    int j = strlen(str) - 1;
    char temp;
    while(i < j) {
        temp = str[i];
        str[i] = str[j];
        str[j] = temp;
        i++;
        j--;
    }
}

int main() {
    char word[] = "ABCDEFG";
    reverse(word);
    cout << word << endl;
}
