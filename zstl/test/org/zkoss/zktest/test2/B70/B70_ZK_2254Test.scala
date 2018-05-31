package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

@Tags(tags = "B70-ZK-2254.zul")
class B70_ZK_2254Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2254.zul

	Purpose:
		
	Description:
		
	History:
		Mon, Apr 14, 2014  3:08:54 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
	1. Please click the button "open all"
	<separator/>
	2. Scroll down the last one, the "c59" label should be there.
	<zscript>
		<![CDATA[
			public class DataObject {
			
				DefaultTreeModel model;
			
				public DataObject() {
					List children = new ArrayList();
					TreeNode root = new DefaultTreeNode("root", children);
					for (int i = 0; i < 60; i++) {
						List children2 = new ArrayList();
						DefaultTreeNode node = new DefaultTreeNode("c" + i, children2);
						node.add(new DefaultTreeNode("cc" + i));
						root.getChildren().add(node);
					}
					model = new DefaultTreeModel(root);
				}
			
				public DefaultTreeModel getTreeModel() {
					return model;
				}
			
			}	
		
			DataObject dataObject = new DataObject();
		]]>
	</zscript>

	<vlayout style="height:400px" width="300px">

		<!--   <custom-attributes org.zkoss.zul.nativebar="true"/> -->


		<tree model="${dataObject.treeModel}" id="tree" hflex="1" vflex="1">
			<treecols>
				<treecol label="Column" />
			</treecols>
			<template name="model" var="node">
				<treeitem>
					<treerow>
						<treecell>
							<checkbox onClick="" onCreate="" />
							<label value="${each.data}" />
						</treecell>
					</treerow>
				</treeitem>
			</template>
		</tree>
		<button onClick="" label="open all" >
			<attribute name="onClick">
				<![CDATA[
					DefaultTreeModel treeModel = dataObject.getTreeModel();
					TreeNode root = treeModel.getRoot();
					List children = root.getChildren();
					treeModel.setOpenObjects(children);
				]]>
			</attribute>
		</button>
		<button label="close all" >
			<attribute name="onClick">
				<![CDATA[
					DefaultTreeModel treeModel = dataObject.getTreeModel();
					TreeNode root = treeModel.getRoot();
					List children = root.getChildren();
					Set remainingOpen = new HashSet(treeModel.getOpenObjects());
					remainingOpen.removeAll(children);
					treeModel.setOpenObjects(remainingOpen);
				]]>
			</attribute>
		</button>
	</vlayout>

</zk>
"""
    runZTL(zscript,
      () => {
        click(jq(".z-button"))
        waitResponse()
        sleep(5000)
        verScroll(jq(".z-tree").toWidget(), 1.0)
        verifyTrue("the 'c59' label should be there.", jq(".z-label:contains(c59)").exists)
      })

  }
}