package com.example.tuquyet.javaproject.spellcheckrule;


import static com.example.tuquyet.javaproject.spellcheckrule.Charcode.nguyenAmDauSacvaNang;


public class Rule {
    public static boolean checkValid(Xau x) {
        if (checkDoDai(x) == false) return false;    //kiem tra do dai phai <=7
        if (checkCauTruc(x) == false) return false;    //kiem tra cau truc p-n-p hop ly khong
        if (checkPhuAmDacBiet(x) == false)
            return false;    //kiem tra nguyen am di kem voi gh,ngh va g,ng,c
        if (checkPhuAmQ(x) == false) return false;    //kiem tra sau q phai la u
        if (checkPhuAmK(x) == false)
            return false;    //kiem tra sau k phai la nguyen am dac biet hoac y
        if (checkNguyenAmDai(x) == false)
            return false;    //nguyen am dai 3 (trừ "uyê") hoac nguyen am dai 2 duoi i,o,u,y (trừ uy,oo) khong duoc co phu am cuoi
        if (checkNguyenAmNgan(x) == false) return false;    // nguyen am ă va â phai co phu am cuoi
        if (checkPhuAmNgat(x) == false)
            return false;    //kiem tra nguyen am khi phu am la c, ch, p, t
        return true;
    }

    public static boolean checkCauTruc(Xau x) {
        return x.tachXau();
    }

    public static boolean checkDoDai(Xau x) {
        if (x.xau.length() > 7) return false;
        return true;
    }

    public static boolean checkPhuAmDacBiet(Xau x) {
        if ((x.phu_am_dau.compareTo("gh") == 0) || (x.phu_am_dau.compareTo("ngh") == 0)) {
            char tmp = x.xau.charAt(x.phu_am_dau.length());
            return (x.isNguyenAmDacBiet(tmp));
        }
        if ((x.phu_am_dau.compareTo("g") == 0) || (x.phu_am_dau.compareTo("ng") == 0) || (x.phu_am_dau.compareTo("c") == 0)) {
            char tmp = x.xau.charAt(x.phu_am_dau.length());
            if (x.phu_am_dau.compareTo("g") == 0) { //rieng g co the dung truoc i
                if ((tmp == 'i') || (tmp == 'ì') || (tmp == 'ỉ')) return true;
            }
            return (!x.isNguyenAmDacBiet(tmp));
        }
        return true;
    }

    public static boolean checkPhuAmK(Xau x) {
        if (x.phu_am_dau.compareTo("k") == 0) {
            char tmp = x.xau.charAt(1);
            if ((x.isNguyenAmDacBiet(tmp) == false) && (tmp != 'y') && (tmp != 'ỳ') && (tmp != 'ý') && (tmp != 'ỷ') && (tmp != 'ỹ') && (tmp != 'ỵ'))
                return false;
        }
        return true;
    }

    public static boolean checkPhuAmQ(Xau x) {
        if (x.phu_am_dau.compareTo("q") == 0) {
            char tmp = x.xau.charAt(1);
            if ((tmp != 'u') && (tmp != 'ù') && (tmp != 'ú') && (tmp != 'ủ') && (tmp != 'ũ') && (tmp != 'ụ'))
                return false;
            if (x.xau.length() == 2) return false;
            if (x.isPhuAm(String.valueOf(x.xau.charAt(2))) == true) return false;
        }
        return true;
    }

    public static boolean checkNguyenAmDai(Xau x) {
        if (x.so_nguyen_am == 3) {
            if ((x.nguyen_am.compareTo("uyê") != 0) && (x.nguyen_am.compareTo("uyề") != 0) &&
                    (x.nguyen_am.compareTo("uyế") != 0) && (x.nguyen_am.compareTo("uyể") != 0) &&
                    (x.nguyen_am.compareTo("uyễ") != 0) && (x.nguyen_am.compareTo("uyệ") != 0)) {
                if (x.phu_am_cuoi.compareTo("") != 0) return false;
            }
        }
        if (x.so_nguyen_am == 2) {
            if ((x.nguyen_am.charAt(1) == 'i') || (x.nguyen_am.charAt(1) == 'o') || (x.nguyen_am.charAt(1) == 'y') || (x.nguyen_am.charAt(1) == 'u'))
                if ((x.nguyen_am.compareTo("uy") != 0) && (x.nguyen_am.compareTo("ùy") != 0) &&
                        (x.nguyen_am.compareTo("úy") != 0) && (x.nguyen_am.compareTo("ủy") != 0) &&
                        (x.nguyen_am.compareTo("ũy") != 0) && (x.nguyen_am.compareTo("ụy") != 0) && (x.nguyen_am.compareTo("oo") != 0))
                    if (x.phu_am_cuoi.compareTo("") != 0) return false;
        }
        return true;
    }

    public static boolean checkNguyenAmNgan(Xau x) {
        if (x.nguyen_am.length() == 1) {
            if ("ăắằẳẵặâấầẩẫậ".contains(x.nguyen_am) == true) {
                if (x.phu_am_cuoi.compareTo("") == 0) return false;
            }
        }
        return true;
    }

    public static boolean checkPhuAmNgat(Xau x) {
        if ((x.phu_am_cuoi.compareTo("c") == 0) || (x.phu_am_cuoi.compareTo("ch") == 0) || (x.phu_am_cuoi.compareTo("p") == 0) || (x.phu_am_cuoi.compareTo("t") == 0)) {
            for (int i = 0; i < nguyenAmDauSacvaNang.length; i++) {
                if (x.nguyen_am.compareTo(nguyenAmDauSacvaNang[i]) == 0) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

}
