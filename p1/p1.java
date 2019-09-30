import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import java.io.*;

class p1 {

    private String[] jie = { " ", "(", ")", "{", "}", "\0" };
    private String[] yun = { "+", "-", "*", "/", "&", "|", "^", "%", "=" };
    private String[] zifuchuan = { "\"", "\'" };
    private String[] guanjianzi = { "if", "else", "for", "while" };
    private List<String> list_biaoshifu = new ArrayList<String>();
    private List<String> list_num = new ArrayList<String>();
    private List<String> list_zifuchuan = new ArrayList<String>();
    private List<String> list_guanjianzi = new ArrayList<String>();
    p1(){

    }

    p1(biao b){
        this.jie = b.toArr(b.jie);
        this.yun = b.toArr(b.yun);
        this.zifuchuan = b.toArr(b.zifuchuan);
        this.guanjianzi = b.toArr(b.guanjianzi);
    }
    public static boolean isNum(String S) {
        for (int i = 0; i < S.length(); i++) {
            if (!(S.charAt(i) >= '0' && S.charAt(i) <= '9' || S.charAt(i) == '.')) {
                return false;
            }
        }
        return true;
    }

    public static String[] cutString(String S, int cur) {

        String[] sArr = { "", "" };
        for (int i = 0; i < S.length(); i++) {
            if (i < cur) {
                sArr[0] += S.charAt(i);
            } else {
                sArr[1] += S.charAt(i);
            }
        }
        return sArr;
    }

    public static boolean cmpString(String[] arr, String S) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(S)) {
                return true;
            }
        }
        return false;
    }

    public  void cmp(String S) {
        try{
            System.setOut(new PrintStream(new FileOutputStream("p1/output.txt", true)));
            for (int i = 0, isString = 0; i < S.length(); i++) {
                String a = new String() + S.charAt(i);
                if (p1.cmpString(jie, a) || p1.cmpString(yun, a) || p1.cmpString(zifuchuan, a)) {
                    if (i == 0) {
    
                        if (p1.cmpString(zifuchuan, a)) {
                            if (isString == 0) {
                                isString = 2;
                            }
                            isString--;
                            System.out.printf("(%c, 字符串标记符)\n", S.charAt(i));
                        } else if (p1.cmpString(jie, a)) {
                            System.out.printf("(%c, 界符)\n", S.charAt(i));
                        } else {
                            System.out.printf("(%c, 运算符)\n", S.charAt(i));
                        }
                        S = p1.cutString(S, 1)[1];
                    } else {
                        String[] req;
                        String ans;
                        req = p1.cutString(S, i);
                        ans = req[0];
                        S = req[1];
                        if (isString == 1) {
                            this.list_zifuchuan.add(ans);
                            System.out.printf("(%s, 字符串):%d\n", ans, this.list_zifuchuan.size()-1);
                        } else if (p1.isNum(ans)) {
                            this.list_num.add(ans);
                            System.out.printf("(%s, 数字常量):%d\n", ans, this.list_num.size()-1);
                        } else if (p1.cmpString(guanjianzi, ans)) {
                            this.list_guanjianzi.add(ans);
                            System.out.printf("(%s, 关键字):%d\n", ans, this.list_guanjianzi.size()-1);
                        } else {
                            this.list_biaoshifu.add(ans);
                            System.out.printf("(%s, 标识符):%d\n", ans, this.list_biaoshifu.size()-1);
                        }
                    }
                    i = -1;
                }
            }
        } catch(Exception e){
            System.out.println("异常");
        }
    }

    public void showList(){
        System.out.println("-----------------------------------------------------");
        for(int i = 0; i < this.list_biaoshifu.size(); i++){
            System.out.printf("<标识符，%s>:%d\n", this.list_biaoshifu.get(i), i);
        }
        for(int i = 0; i < this.list_guanjianzi.size(); i++){
            System.out.printf("<关键字, %s>:%d\n", this.list_guanjianzi.get(i), i);
        }
        for(int i = 0; i < this.list_zifuchuan.size(); i++){
            System.out.printf("<字符串, %s>:%d\n", this.list_zifuchuan.get(i), i);
        }
        for(int i = 0; i < this.list_num.size(); i++){
            System.out.printf("<数字常量, %s>:%d\n", this.list_num.get(i), i);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("p1/biao.txt");
        biao b1 = new biao(file);
        p1 test = new p1(b1);
        File file2 = new File("p1/input.txt");
        Scanner in = new Scanner(file2);
        while(in.hasNext()){
            test.cmp(in.nextLine());
        }
        test.showList();
        // System.out.println(b1.toString());
    }
     
}

class biao {
    public List<String> jie = new ArrayList<String>();
    public List<String> yun = new ArrayList<String>();
    public List<String> zifuchuan = new ArrayList<String>();
    public List<String> guanjianzi = new ArrayList<String>();

    biao(File f) {
        
        try {
            Scanner in = new Scanner(f);
            this.k_push(in);
            this.push("1", " ");
            this.push("1", "\0");
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void push(String s1, String s2){
        if(s1.equals("1")){
        //    System.out.println("jie push");
            this.jie.add(s2);
        } else if(s1.equals("2")){
            this.yun.add(s2);
        } else if(s1.equals("3")){
            this.zifuchuan.add(s2);
        } else if(s1.equals("4")){
            this.guanjianzi.add(s2);
        }
    }

    public void k_push(Scanner k_in){
        while(k_in.hasNext()){
            String s = k_in.nextLine();
            StringTokenizer st = new StringTokenizer(s);
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            this.push(s1, s2);
            // System.out.println(this.toString());
        }
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.jie.toString() + this.yun.toString() + this.zifuchuan.toString() + this.guanjianzi.toString();
    }

    public String[] toArr(List<String> a){
        String[] arr_a = new String[a.size()];
        for(int i = 0; i < a.size(); i++){
            arr_a[i] = a.get(i);
        }
        return arr_a;
    }
    // public static void main(String[] args) {
    //     File file = new File("p1/biao.txt");
    //     biao b1 = new biao(file);
    //     System.out.println(b1.toString());
    // }
}