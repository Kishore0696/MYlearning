#include<bits/stdc++.h>
using namespace std;

struct node{
        int data;
        node* next;
    };

class linkedlist{
private:
    node* head;
public:
    linkedlist(){
        head=nullptr;
    }

    void appendatbegining(int val){
        node* newnode=new node();
        newnode->data=val;
        newnode->next=head;
        head=newnode;
    }
/*
    void append(int val){
        node* newnode=new node();
        newnode->data=val;
        newnode->next=nullptr;
        if(head==nullptr){
            head=newnode;
            return;
        }
        node* temp=head;
        while(temp->next!=nullptr){
            temp=temp->next;
        }
        temp->next=newnode;
    }
*/
    void display(){
        node* temp=head;
        while(temp!=nullptr){
            cout<<temp->data<<"->";
            temp=temp->next;
        }
        cout<<"null"<<endl;
    }
};

int main(){
    int n,val;
    linkedlist list;
    cout<<"enter the no.of data to be appended:";
    cin>>n;
    for(int i=0;i<n;i++){
        cin>>val;
        list.appendatbegining(val);
    }
    list.display();
    return 0;
}