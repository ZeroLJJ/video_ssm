package util;

import java.util.List;

/**
 * Created by Zero on 2017/2/26.
 */
public class Page {
    private List list; // 要返回的某一页的记录列表,不指定类型,这样就可以适应各种类型
    private int allRow; // 总记录数
    private int totalPage; // 总页数
    private int currentPage; // 当前页
    private int pageSize; // 每页的记录数
    private boolean isFirstPage; // 是否为第一页
    private boolean isLastPage; // 是否为最后一页
    private boolean hasPreviousPage; // 是否有前一页
    private boolean hasNextPage; // 是否有下一页

    /**
     * 计算总页数 静态方法
     *
     * @param pageSize
     *            每页的记录数
     * @param allRow
     *            总记录数
     * @return 总页数
     */
    public static int countTotalPage(final int pageSize, final int allRow) {
        int totalPage = (allRow % pageSize == 0 ? allRow / pageSize : allRow
                / pageSize + 1);
        return totalPage;
    }

    /**
     * 计算当前页开始的记录
     *
     * @param pageSize
     *            每页记录数
     * @param currentPage
     *            当前第几页
     * @return 当前页开始记录号
     */
    public static int countOffset(final int pageSize, final int currentPage) {
        final int offset = pageSize * (currentPage - 1);
        return offset;
    }

    /**
     * 设置是否有前后页，第一页和最后一页信息
     * @param page 存储页面信息的对象
     * @param currentPage 当前页
     * @param totalPage 总页数
     */
    public static void init(Page page, final int currentPage, final int totalPage) {
        if (totalPage == 0) { // 总页数只有0页时
            page.setFirstPage(false);
            page.setLastPage(false);
            page.setHasPreviousPage(false);
            page.setHasNextPage(false);
        }else if (totalPage == 1) { // 总页数只有一页时
            page.setFirstPage(true);
            page.setLastPage(true);
            page.setHasPreviousPage(false);
            page.setHasNextPage(false);
        } else if (currentPage == 1) { // 当前页为第一页时
            page.setFirstPage(true);
            page.setLastPage(false);
            page.setHasPreviousPage(false);
            page.setHasNextPage(true);
        } else if (currentPage == totalPage) { // 当前页为最后一页时
            page.setFirstPage(false);
            page.setLastPage(true);
            page.setHasPreviousPage(true);
            page.setHasNextPage(false);
        } else { // 当前页既不为第一页也不为最后一页时
            page.setFirstPage(false);
            page.setLastPage(false);
            page.setHasPreviousPage(true);
            page.setHasNextPage(true);
        }
    }

    /**
     * 计算当前页，若为0或者请求的URL中没有“?pageNo = ”则用1代替
     *
     * @param pageNo
     *            传入的参数（可能为空，即0 则返回1）
     * @return
     */
    public static int countCurrentPage(int pageNo) {
        final int curPage = (pageNo == 0 ? 1 : pageNo);
        return curPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getAllRow() {
        return allRow;
    }

    public void setAllRow(int allRow) {
        this.allRow = allRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
