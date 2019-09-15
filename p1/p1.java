import java.util.Scanner;

class p1{
    
    String[] jie = {" ", "(", ")", "{", "}", "\0"};
    String[] yun = {"+", "-", "*", "/", "&", "|", "^", "%"};
    String[] zifuchuan = {"\"", "\'"};
    String[] guanjianzi = {"if", "else", "for", "while"};

    public boolean isNum(String S){
        for(int i = 0; i < S.length(); i++){
            if(!(S.charAt(i) >= '0' && S.charAt(i) <= '9')){
                return false;
            }
        }
        return true;
    }

    public String[] cutString(String S, int cur){
        
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

    public boolean cmpString(String[] arr, String S){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals(S)){
                return true;
            }
        }
        return false;
    }

    public void cmp(String S){
        for(int i = 0, isString = 0; i < S.length(); i++){
            String a = new String()+S.charAt(i);
            if(new p1().cmpString(jie, a) || new p1().cmpString(yun, a) || new p1().cmpString(zifuchuan, a)){
                if(i == 0){
                    
                    if(new p1().cmpString(zifuchuan, a)){
                        if(isString == 0){
                            isString = 2;
                        }
                        isString--;
                        System.out.printf("(%c, 字符串标记符)\n", S.charAt(i));
                    } else if(new p1().cmpString(jie, a)){
                        System.out.printf("(%c, 界符)\n", S.charAt(i));
                    } else{
                        System.out.printf("(%c, 运算符)\n", S.charAt(i));
                    }
                    S = new p1().cutString(S, 1)[1];
                } else{
                    String[] req;
                    String ans;
                    req = new p1().cutString(S, i);
                    ans = req[0];
                    S = req[1];
                    if(isString == 1){
                        System.out.printf("(%s, 字符串)\n", ans);
                    } else if(new p1().isNum(ans)){
                        System.out.printf("(%s, 数字常量)\n", ans);
                    } else if(new p1().cmpString(guanjianzi, ans)){
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
            new p1().cmp(S);
        }
        in.close();
    }
}