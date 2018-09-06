import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {
    private static int[] arr={2,4,6,8};
    private static int[] arr2={1,3,5,7,9};
    /*
    * 冒泡排序》相邻的两个数比较 前面的比后面的数大  则交换位置
    * */
    public static void sort1(){
        for(int m=0;m<arr.length-1;m++){
            boolean ex=true;
            for(int n=0;n<arr.length-m-1;n++){
                if(arr[n]>arr[n+1]){
                    int temp=arr[n];
                    arr[n]=arr[n+1];
                    arr[n+1]=temp;
                    ex=false;
                }
            }
            if(ex){
                break;
            }
        }
        List<Integer> list= Arrays.stream(arr).boxed().collect(Collectors.toList());
        list.forEach(p->System.out.println(p));
    }
    /*
    * 快速排序》把第一个数当标识  比它小的放左边  比它大的放左边  再对两边递归调用
    * */
    public static void sort2(int [] arr,int start,int end){
        if (start >= end)
            return;
        int i=start;
        int j=end;
        int index=arr[i];
        while (i<j){
            while (i<j&&arr[j]>index){
                j--;
            }
            if(i<j){
                arr[i]=arr[j];
                i++;
            }
            while (i<j&&arr[i]<index)
                i++;
            if(i<j){
                arr[j]=arr[i];
                j--;
            }
        }
        arr[i]=index;
        sort2(arr,start,i-1);
        sort2(arr,i+1,end);
    }
    public static void test3(){
        sort2(arr,0,arr.length-1);
        List<Integer> list= Arrays.stream(arr).boxed().collect(Collectors.toList());
        list.forEach(p->System.out.println(p));
    }
    /*
    * 选择排序》每次把数组里面最小的数拿出来  放在最前面 比较的长度减一
    * */
    public static void sort3(){
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp =arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        List<Integer> list= Arrays.stream(arr).boxed().collect(Collectors.toList());
        list.forEach(p->System.out.println(p));
    }
    /*
    * 插入排序》让前N个数有序  后面的数一次比较找到自己在有序数组中的位置后插入
    * */
    public static void sort4(){
        for(int i=1;i<arr.length;i++){
            for(int j=i;j>0;j--){
                if(arr[j-1]>arr[j]){
                    int temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
            }
        }
        List<Integer> list= Arrays.stream(arr).boxed().collect(Collectors.toList());
        list.forEach(p->System.out.println(p));
    }
    /*
    * 堆排序》先初始化大堆
    * */
    public static void sort5(){
        int size=arr.length;
        for(int i=size/2-1;i>=0;i--){
            Heap(i,size);
        }//初始化大堆
        while (size>1){
            Swap(0,--size);
            Heap(0,size);
        }//将堆底和堆顶元素互换后 再对size--的堆进行堆排序
        List<Integer> list= Arrays.stream(arr).boxed().collect(Collectors.toList());
        list.forEach(p->System.out.println(p));
    }
    /*
    * 转换为大堆
    * */
    static void Heap(int i, int size)  // 将以arr[i]节点为根的堆转换为大堆
    {
        int left_child = 2 * i + 1;         // 左孩子
        int right_child = 2 * i + 2;        // 右孩子
        int max = i;                        // 在arr[i]放置最大值
        if (left_child < size && arr[left_child] > arr[max])
            max = left_child;
        if (right_child < size && arr[right_child] > arr[max])
            max = right_child;
        if (max != i)
        {
            Swap(i, max);                // max不等于i时 把i和max的位置交换
            Heap( max, size);            // 节点值发生变化 可能以该节点为根的堆不是大堆 递归调用
        }
    }
    static void Swap(int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    /*
    * 并归排序
    * */
    static void sort6(){
        int[] arr3 = new int[arr.length+arr2.length];
        int i=0,j=0,k=0;
        while(i<arr.length&&j<arr2.length){
            if(arr[i]<arr2[j])
                arr3[k++]=arr[i++];
            else
                arr3[k++]=arr2[j++];
        }
        while (i<arr.length)
            arr3[k++]=arr[i++];
        while (j<arr2.length)
            arr3[k++]=arr2[j++];
     /*   if(arr[arr.length-1]>arr2[arr2.length-1])
            arr3[arr3.length-1]=arr[arr.length-1];
        else
            arr3[arr3.length-1]=arr2[arr2.length-1];*/
        List<Integer> list= Arrays.stream(arr3).boxed().collect(Collectors.toList());
        list.forEach(p->System.out.println(p));
    }
    /*
    * 希尔排序》间隔使用插入排序
    * */
    static void sort7(){
        int n=arr2.length/2;
        while (n>0){
            for(int i=n;i<arr2.length;i++){
               /* int temp=arr2[i];
                int j=i-n;
                for(;j-n>=0;j-=n){
                    if(arr2[j]>temp){
                        arr2[j+n]=arr2[j];
                    }
                }
                arr2[j]=temp;*/
                int j = i;
                int temp = arr2[j];
                if(arr2[j]<arr2[j-n]){
                    while(j-n>=0 && temp<arr2[j-n]){
                        //移动法
                        arr2[j] = arr2[j-n];
                        j-=n;
                    }
                    arr2[j] = temp;
                }
            }
            n=n/2;
        }
        List<Integer> list= Arrays.stream(arr2).boxed().collect(Collectors.toList());
        list.forEach(p->System.out.println(p));
    }
}
