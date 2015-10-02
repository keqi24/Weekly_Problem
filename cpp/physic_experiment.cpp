#include <cstdio>

void quickSort(double arr[], int left, int right) {
      int i = left, j = right;
      double tmp;
      int pivot = arr[(left + right) / 2];
 
      /* partition */
      while (i <= j) {
            while (arr[i] < pivot)
                  i++;
            while (arr[j] > pivot)
                  j--;
            if (i <= j) {
                  tmp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = tmp;
                  i++;
                  j--;
            }
      };
 
      /* recursion */
      if (left < j)
            quickSort(arr, left, j);
      if (i < right)
            quickSort(arr, i, right);
}

void printArray(double data[], int N) {
    for (int i=0; i<N; ++i) {
        printf("%lf, ", data[i]);
    }
    printf("\n");
}

double core(double data[], int N, double D) 
{
    if (N == 1)
    {
        return 0;
    }

    quickSort(data, 0,  N-1);

    // int index = 1;
    // double time = 0;
    // double newPosition = data[0];
    // while(index < N )
    // {
    //     double distance = data[index] - newPosition;
    //     if (distance > D) 
    //     {
    //         double tmpTime = (distance - time - D) / 2; 
    //         if (tmpTime > 0) 
    //         {
    //             time += tmpTime;
    //             newPosition = data[index] - time;
    //         } else 
    //         {
    //             newPosition += D;
    //         }
    //     } else if (distance < D) 
    //     {
    //         newPosition += (distance + time) < D ? (distance + time) : D; 
    //     } else 
    //     {
    //         newPosition += D;
    //     }
    //     ++index;
    // }

    return 0;
}
void execute() 
{
    int caze = 1;
    int N = 0;
    double D = 0;
    double data[10001];
    
    while(true) 
    {
        scanf("%d", &N);
        if (N == 0)
        {
            break;
        }
        for(int i=0; i<N; ++i) 
        {
            scanf("%lf", &data[i]);
        }
        scanf("%lf", &D);


        double time = core(data, N, D);
        printf("Case %d: %.3lf\n", caze, time);
        ++caze;
    }
}


int main() 
{
    execute();
}
