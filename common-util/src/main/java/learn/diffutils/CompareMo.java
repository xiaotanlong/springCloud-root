package learn.diffutils;

/**
 * @Auther: jianglong.Tan
 * @Date: 2019/4/30 10:56
 * @Description:  对比结果类
 */
public class CompareMo {
    private int id;
    private String oldText;
    private String newText;
    private String type;
    //行宽
    private int totalNum;
    private int changeBeginNum;//差异开始下表
    private int changeEndNum;//差异结束下表

    public CompareMo(int id, String oldText, String newText, String type) {
        this.id = id;
        this.oldText = oldText;
        this.newText = newText;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOldText() {
        return oldText;
    }

    public void setOldText(String oldText) {
        this.oldText = oldText;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
