import sys

N = int(sys.stdin.readline())
arr = [sys.stdin.readline().rstrip() for _ in range(N)]

arr = list(set(arr)) # set():중복값 제거함수 -> 제거 후 리스트에 다시 담기
arr.sort() # 사전 순으로 정렬
arr.sort(key=len) # 길이 순으로 정렬
# 길이 순으로 먼저 정렬하고 사전순으로 정렬하면 길이에 상관없이 사전순으로 정렬이 뒤죽박죽 됨 

for i in arr:
    print(i)
