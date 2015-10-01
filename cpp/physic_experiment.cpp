#include <cstdio>

void execute() 
{
    int caze = 1;
    int N = 0;
    int D = 0;
    int data[10000];
    
    while(true) 
    {
        scanf("%d", &N);
        if (N == 0)
        {
            break;
        }
        float usedTime = 0.0f;
        for(int i=0; i<N; ++i) 
        {
            scanf("%f", &data[i]);
        }
        scanf("%f", &D);


        ++caze;
    }
}

float core(float[] data, int N, float D) 
{
    if (N == 1)
    {
        return 0;
    }
    int i = 1;
    float lastH = data[0];
    while(i < N && (data[i]-lastH) <= D )
    {
        ++i;
    }
    float firstNH = i;
    float NHGroupTail = i;
    float time = 0.0;
    float 
    while(i<N) 
    {
        while()
        ++i;
    }
}

int main() 
{
    execute();
}
