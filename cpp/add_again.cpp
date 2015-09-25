#include <cstdio>

void execute() 
{
    int factorial[13] = { };
    factorial[0] = 1;
    for (int i=1; i<13; ++i) 
    {
        factorial[i] = factorial[i-1] * i;
    }

    int N = 0;
    int oneResult = 0;
    int data[12] = { };
    int count[10] = { };
    while (true)
    {
        scanf("%d", &N);
        if (N == 0) 
        {
            break;
        }
        else if (N == 1) 
        {
            scanf("%d", &oneResult);
            printf("%d\n", oneResult);
            continue;
        }

        for (int i=0; i<10; ++i) 
        {
            count[i] = 0;
        }
        int readCount = 0;
        while (readCount<N) 
        {
            scanf("%d", &data[readCount]);
            readCount++;
        }

        for (int i=0; i<N; ++i)
        {
            count[data[i]]++;
        }
        int times = factorial[N];
        for (int i=0; i<10; ++i)
        {
            times = times / factorial[count[i]];
        }

        int sum = 0;
        for (int i=0; i<N; ++i)
        {
            sum += data[i];
        }

        unsigned long long result = 0;
        unsigned long long num = times * sum / N;

        for (int i=0; i<N; ++i) 
        {
            result  = result * 10 + num;
        }

        printf("%llu\n", result);
    }
}

int main() 
{
    execute();
}
