nums=[50] 
print(nums.index(50))
k=7
valid=[]
for i in nums:
    if i%k==0:
        valid.append(i)
nvalid=sorted(valid)
for j in range(len(nvalid)):
    if nvalid[j]!=(j+1)*k:
        print((j+1)*k)
        break
else:        
    print(nvalid[0]+k)