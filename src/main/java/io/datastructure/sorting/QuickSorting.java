package io.datastructure.sorting;

public class QuickSorting {

  //based on divide-and-conquer,
 //  Algorithm Steps
  //  We choose an element from the list, called the pivot. We'll use it to divide the list into two sub-lists.
  //  We reorder all the elements around the pivot – the ones with smaller value are placed before it, and all the
  //  elements greater than the pivot after it. After this step, the pivot is in its final position. This is the
  //  important partition step.
  //  We apply the above steps recursively to both sub-lists on the left and right of the pivot.
  public int partion(int arr[],int left, int right)
  {
    //take first element as a pivot
    int pivot = arr[left];
    // i is index that should trace the smallest elements than pivot.
    // at last we will swap element at index i with pivot.
    //means before ith index all elements should be less than pivot.
    int i = left;

    for(int j = left+1;j <= right;j++)
    {
      if(arr[j] < pivot)
      {
        i++;    //small element found we should increment i.
        swap(arr,i,j);   //swap the elements so that small element will come to correct position.
      }
    }
    // after loop ends before ith index all small elements were placed.
    // now swap i index with the pivot.
    swap(arr,i,left);

    return i;
  }

  public void quicksort(int arr[],int low, int high)
  {
    if(low < high)
    {
      // dividing array with the pivot.
      int p = partion(arr,low,high);
      quicksort(arr,low,p-1);
      quicksort(arr,p+1,high);
    }
  }

  public void swap(int arr[],int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String args[])
  {
    int arr[] = {11,8,5,4,3,2,1,9};
    QuickSorting obj = new QuickSorting();

    System.out.print("elements before sorting\n");
    for (int i = 0; i < arr.length;i++)
    {
      System.out.print(arr[i]+" ");
    }

    obj.quicksort(arr,0,7);
    System.out.print("\nelements after sorting using quick sort\n");
    for (int i = 0;i < arr.length;i++)
    {
      System.out.print(arr[i]+" ");
    }
  }
}
