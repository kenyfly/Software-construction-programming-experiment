import java.util.Scanner;

class p1{
    
    private static String[] jie = {" ", "(", ")", "{", "}", "\0"};
    private static String[] yun = {"+", "-", "*", "/", "&", "|", "^", "%", "="};
    private static String[] zifuchuan = {"\"", "\'"};
    private static String[] guanjianzi = {"if", "else", "for", "while"};

    public static boolean isNum(String S){
        for(int i = 0; i < S.length(); i++){
            if(!(S.charAt(i) >= '0' && S.charAt(i) <= '9' || S.charAt(i) == '.')){
                return false;
            }
        }
        return true;
    }

    public static String[] cutString(String S, int cur){
        
        String[] sArr = {"", ""};
        for(int i = 0; i < S.length(); i++){
            if(i < cur){
                sArr[0] += S.charAt(i);
            } else{
                sArr[1] += S.charAt(i);
            }
        }
        return sArr;
    }

    public static boolean cmpString(String[] arr, String S){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals(S)){
                return true;
            }
        }
        return false;
    }

    public static void cmp(String S){
        for(int i = 0, isString = 0; i < S.length(); i++){
            String a = new String()+S.charAt(i);
            if(p1.cmpString(jie, a) || p1.cmpString(yun, a) || p1.cmpString(zifuchuan, a)){
                if(i == 0){
                    
                    if(p1.cmpString(zifuchuan, a)){
                        if(isString == 0){
                            isString = 2;
                        }
                        isString--;
                        System.out.printf("(%c, 字符串标记符)\n", S.charAt(i));
                    } else if(p1.cmpString(jie, a)){
                        System.out.printf("(%c, 界符)\n", S.charAt(i));
                    } else{
                        System.out.printf("(%c, 运算符)\n", S.charAt(i));
                    }
                    S = p1.cutString(S, 1)[1];
                } else{
                    String[] req;
                    String ans;
                    req = p1.cutString(S, i);
                    ans = req[0];
                    S = req[1];
                    if(isString == 1){
                        System.out.printf("(%s, 字符串)\n", ans);
                    } else if(p1.isNum(ans)){
                        System.out.printf("(%s, 数字常量)\n", ans);
                    } else if(p1.cmpString(guanjianzi, ans)){
                        System.out.printf("(%s, 关键字)\n", ans);
                    } else{
                        System.out.printf("(%s, 标识符)\n", ans);
                    }
                }
                i = -1;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("test!");
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String S = in.nextLine();
            S += "\0";
            System.out.printf("%s %d\n", S, S.length());
            p1.cmp(S);
        }
        in.close();
    }
}