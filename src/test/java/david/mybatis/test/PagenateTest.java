package david.mybatis.test;

import org.junit.Test;

import david.mybatis.demo.DemoRun;
import david.mybatis.model.SortDirectionEnum;



public class PagenateTest {

	@Test
	public void testPageNate() {
		DemoRun.queryVisitorListWithPagenate(0, 100, "id", SortDirectionEnum.DESC.toString());
	}
	
	@Test
	public void test6_10() {
		DemoRun.queryVisitorListWithPagenate(1, 5, "id", SortDirectionEnum.DESC.toString());
	}
	
	
}
