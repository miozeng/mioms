
package com.mioms.core.util;

import java.util.List;

public class Page<T> {

	private int totalPage;

	private int resultCount;

	private int page;

	private int pageSize;

	private List<T> objectList;



	public Page(int resultCount, int pageSize, List<T> objectList) {
		super();
		
		if (resultCount > 0) {
			this.resultCount = resultCount;
		}
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
		if (resultCount > 0 && pageSize > 0) {
			this.totalPage = (int) ((resultCount + pageSize - 1) / pageSize);
		}
		this.objectList = objectList;
	}

	public Page(int resultCount, int pageSize) {

		if (resultCount > 0) {
			this.resultCount = resultCount;
		}
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
		if (resultCount > 0 && pageSize > 0) {
			this.totalPage = (int) ((resultCount + pageSize - 1) / pageSize);
		}
		this.page = 1;
	}


	public int getPreviousPage() {
		if (this.page - 1 <= 0) {
			return 1;
		}	else {
			return (this.page - 1);
		}
	}

	public int getNextPage() {
		if (this.page + 1 >= totalPage) {
			return totalPage;
		}		else {
			return (this.page + 1);
		}
	}

	public int getFirstItemPos() {
		int temp = (page - 1) * pageSize;
		if(temp<0)temp=0;
		return temp;
	}
	public int getMaxItemNum() {
		int maxItemNum = 0;
		if (resultCount <= pageSize) {
			maxItemNum = resultCount;
		}		else if ((resultCount - (page - 1) * pageSize) >= pageSize) {
			maxItemNum = pageSize;
		}		else {
			maxItemNum = (resultCount - (page - 1) * pageSize);
		}
		return maxItemNum;
	}
	
	public int getEndItemPos(){
		return this.getFirstItemPos()+this.getMaxItemNum();
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page > totalPage) {
			this.page = totalPage;
		}		else if (page <= 0) {
			this.page = 1;
		}		else {
			this.page = page;
		}
	}

	public int getPageSize() {
		return pageSize;
	}


	public int getResultCount() {
		return resultCount;
	}

	public int getTotalPage() {
		return totalPage;
	}


	public List<T> getObjectList() {
		return objectList;
	}


	public void setObjectList(List<T> objectList) {
		this.objectList = objectList;
	}



}
