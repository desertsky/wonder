List of bugs/enhancements that have been done since: Wonder54 #169 (10/14/2011)

Bugs since Wonder_169_10_14_2011:
---------------------------------
[] AjaxSortableList.java - Call onUpdate binding javascript function directly to prevent d&d lag

[] wonder.js - AjaxOnDemand loadedCSS needs special case for IE

[] AjaxFlexibleFileUpload.java
	[] refreshState
		[] if upload has not started need to check isDone and isFailed to handle
			file without an EOF marker and exceptions from the file upload handler
	[] uploadFailed: pass progress failure (exception) to parent component
	[] added onClickBefore binding to be able to execute javascript before upload starts
	[] changed javascript:void(); in WOD file to javascript:void(0);
		[] fixed FF issues (especially when cancelling an upload)
	[] added hideInComment = false in WOD file for IE 8 because javascript comments caused parse issue for IE 8
