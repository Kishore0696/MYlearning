#include<bits/stdc++.h>
using namespace std;

int main(){
    
    char s1[10]="silent",s2[10]="listen";
    map<char,int>fm;
    for(char x : s1){
        fm[x]++;
    }
    for(char y:s2){
        fm[y]--;
        if(fm[y]==0){
            fm.erase(y);
        }
    }
    if(fm.empty()){
        cout<<"true";
    }
    else{
        cout<<"false";
    }
}