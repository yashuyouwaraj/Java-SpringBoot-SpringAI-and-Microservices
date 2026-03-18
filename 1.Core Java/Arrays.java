public class Arrays {
    public static void main(String[] args) {
        int nums[] = {3,7,10,8};
        nums[1]=5;
        System.out.println(nums[1]);

        int nums2[] = new int[4];
        nums2[0]=4;
		nums2[1]=8;
		nums2[2]=3;
		nums2[3]=9;

        for(int i=0; i<nums2.length;i++){
            System.out.println(nums2[i]);
        }
    }
}
