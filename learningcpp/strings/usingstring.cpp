#include<iostream>
#include<string>
using namespace std;

/*
int main(){
    string a;
    cin>>a;
    cout<<a;
    return 0;
}
*/
/*
int main(){
    string a="hello";
    for(string::iterator hi=a.begin(); hi!=a.end(); hi++){ // or for(char c:a) this will work like for i in a in py
        cout<< *hi<<" ";
    }
    return 0;
}
*/

//using while loop

int main(){
    string a="hello";
    int i=0;
    while(i<a.length()){
        cout<<a[i];
        i++;
    }
    cout<<endl;
    cout<<a[4];
}