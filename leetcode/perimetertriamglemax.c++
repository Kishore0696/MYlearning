#include<bits/stdc++.h>
using namespace std;

int main(){
    vector<int>nums={1,2,1,10};
    vector<int>valid;
    
    for(int i=0;i<nums.size();i++){
        for(int j=1;j<nums.size();j++){
            for(int k=2;k<nums.size();k++){
                if(nums[i]+nums[j]>nums[k] && nums[j]+nums[k]>nums[i] && nums[i]+nums[k]>nums[j]){
                    cout<<nums[i]<<" "<<nums[j]<<" "<<nums[k]<<" "<<endl;
                    valid.push_back(nums[i]+nums[j]+nums[k]);
                }
            }
        }
    }
    int ans=*max_element(valid.begin(),valid.end());
    cout<<ans;
}