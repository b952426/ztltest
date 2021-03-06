package org.zkoss.zktest.test2.theme

import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "IOS,VisionTest")
class IVT_Calendar_Simple_Test extends ZTL4ScalaTestCase {
	def testClick() = {
		val zscript = """
<vlayout>
	<calendar id="cal" />
	<datebox id="db" width="200px" format="yyyy/MM/dd a hh:mm:ss"
		displayedTimeZones="GMT+8,GMT-8" />
		
	<zscript>
		import java.util.Calendar;
		import org.zkoss.zk.ui.util.Clients;
		
		Calendar snapshot = Calendar.getInstance();
		snapshot.set(Calendar.YEAR, 2012);
		snapshot.set(Calendar.MONTH, Calendar.NOVEMBER);
		snapshot.set(Calendar.DATE, 1);
		snapshot.set(Calendar.HOUR, 2);
		snapshot.set(Calendar.MINUTE, 34);
		snapshot.set(Calendar.SECOND, 54);
		snapshot.set(Calendar.AM_PM, Calendar.PM);
		 
		cal.value = db.value = snapshot.getTime();
		
		Clients.evalJavaScript("jq('.z-datebox-button').trigger('click')");
	</zscript>	
</vlayout>
		""";

		runZTL(zscript,
			() => {
				verifyImage();
			});
	}
}
