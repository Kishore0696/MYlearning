#include <iostream>
using namespace std;

int main() {
    char c = 'A';
    int ascii = c; // implicit conversion, char -> int
    cout << "ASCII value of " << c << " is " << ascii << endl;
    return 0;
}
// Output: ASCII value of A is 65 