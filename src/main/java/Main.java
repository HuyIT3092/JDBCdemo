import com.vn.Rectangle;
import com.vn.TestExceptionPropagation;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class Main {

    @Test
    public void test_illegalArgs() {
        Rectangle rec = new Rectangle();


        try {
            System.out.println(rec.width);
            System.out.println(rec.height);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void test_TestExceptionPropagation() {
        TestExceptionPropagation test = new TestExceptionPropagation();
        test.p();
        System.out.println("normal flow...");
    }

    public static int nhapTuoiNhanVien() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap tuoi nhan vien: ");
        int tuoi = sc.nextInt();

        if (tuoi < 18) {
            throw new Exception("Tuoi khong hop le");
        }
        return tuoi;

    }

    public static void main(String[] args) {
        try {
            int tuoi = nhapTuoiNhanVien();
            System.out.println(tuoi);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}