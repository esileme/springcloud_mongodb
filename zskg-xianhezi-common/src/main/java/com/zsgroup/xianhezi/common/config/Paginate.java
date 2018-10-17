package com.zsgroup.xianhezi.common.config;

public class Paginate {

	private int currentPage;
	private int pageSize;
	private int total;

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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getOffset() {
		return ((this.currentPage - 1) * this.pageSize);
	}

	public int getTotalPage() {
		int totalPage;
		if (this.total % this.pageSize == 0)
			totalPage = (this.total / this.pageSize);
		else
			totalPage = (this.total / this.pageSize + 1);
		return totalPage;
	}

}
