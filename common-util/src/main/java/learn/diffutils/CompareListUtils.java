package learn.diffutils;

import difflib.DiffRow;
import difflib.DiffRowGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: jianglong.Tan
 * @Date: 2019/4/30 10:56
 * @Description:
 */
public class CompareListUtils {
    public static final String Result_EQUAL = "EQUAL";//相同
    public static final String Result_INSERT = "INSERT";//插入
    public static final String Result_DELETE = "DELETE";//删除
    public static final String Result_CHANGE = "CHANGE";//改变
    /***
     * 把文件按行读取成list<String>
     * @param filename
     * @return
     */
    private static List<String> fileToLines(String filename) {
        List<String> lines = new LinkedList<String>();
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /***
     * 对文件进行比较
     * INSERT, DELETE, CHANGE, EQUAL
     * @param fromFileName 原来的文件名
     * @param toFileName 新的文件名
     */
    public static List<CompareMo> compareAll(String fromFileName, String toFileName) {
        //转换成行数组
        List<String> original = fileToLines(fromFileName);
        List<String> revised = fileToLines(toFileName);

        final DiffRowGenerator.Builder builder = new DiffRowGenerator.Builder();
        final DiffRowGenerator dfg = builder.build();
        final List<DiffRow> rows = dfg.generateDiffRows(original, revised);
        List<CompareMo> listCompareMo = new ArrayList<>();
        int i=1;
        int oldSize = original.size();
        int newSize = revised.size();
        int insertSize = 0;
        int deleteSize = 0;
        for (final DiffRow diffRow : rows) {
            String tag = diffRow.getTag().toString();
            String oldLine = diffRow.getOldLine();
            String newLine = diffRow.getNewLine();
            //不同行 去校验是添加还是删除的
            if(Result_CHANGE.equals(tag)){
                boolean isInset = false;
                if ((i-insertSize) <= oldSize) {
                    if(oldLine!=null&& oldLine.trim().length()==0){
                        if(!original.get(i-1-insertSize).equals(oldLine)){
                            tag = Result_INSERT;
                            isInset = true;
                            insertSize ++;
                        }
                    }
                }
                if (!isInset) {
                    if ((i-deleteSize) <= newSize) {
                        if(newLine!=null&& newLine.trim().length()==0){
                            if(!revised.get(i-1-deleteSize).equals(oldLine)){
                                tag = Result_DELETE;
                                deleteSize ++;
                            }
                        }
                    }
                }else{//获取差异行  开始位置和结束位置
                    int bigNum = 0,endNum = oldLine.length() > newLine.length()
                            ? newLine.length() : oldLine.length();


                }
            }
            //记录差异行
            listCompareMo.add(new CompareMo(
                    i, oldLine,
                    newLine,
                    tag
            ));
            i++;
        }
        return listCompareMo;
    }

    public static void main(String[] args) {
        List<CompareMo> compares = CompareListUtils.compareAll(
                "E:/testFile/file1.txt",
                "E:/testFile/file2.txt");
        for (CompareMo compare : compares) {
            System.out.println(compare.getId() + " 结果 :" + compare.getType()
                    + ":" + compare.getOldText() + "<>" + compare.getNewText());
        }
    }
}
