<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
function ajaxMessage() {
	var messageId = $("#messageId").val();
	$.post("${ctx}/message/ajaxMessage", {
		"messageId" : messageId
	}, function(data) {
		$("#tc").empty();
		var msgType = data.message.msgType;
		if ("text" == msgType) {
			var html = buildtext(data.message);

			$("#tc").append(html);
		} else {
			var html = buildMuli(data);
			$("#tc").append(html);
		}
	});
}

function buildtext(msg) {
	return "<tr><td><textarea cols='90' rows='14' disabled>" + msg.content
			+ "</textarea></td></tr>";
}

function buildMuli(data) {
	var msgType = data.message.msgType;

	var html = "<tr height='20' bgcolor='#d3d3d3' align='center'><td width='5%'>选择</td><td width='10%'>类型</td><td width='20%'>标题</td><td width='25%'>url</td><td>原文链接</td></tr>";
	var contents = data.contents;
	$
			.each(
					contents,
					function(i, content) {
						html += "<tr>";
						html += "<td align='center'><input type='checkbox' disabled value='"
								+ content.id
								+ "'"
								+ selectIds(data.selectIds, content.id)
								+ "></td>";
						html += "<td align='center'>"
								+ buildMsgType(content.msgType) + "</td>";
						html += "<td>" + content.title + "</td>";
						if ("image" == msgType) {
							html += "<td>" + buildTitle(content.picUrl)
									+ "</td>";
						} else {
							html += "<td>" + buildTitle(content.musicUrl)
									+ "</td>";
						}

						html += "<td>" + buildTitle(content.url) + "</td>";
						html += "</tr>";
					});
	return html;
};

function buildTitle(data) {
	var html = "<a title='" + data + "'>";
	if (data.length > 20) {
		html += data.substr(1, 20) + '...';
	} else {
		html += data;
	}
	html += "</a>";
	return html;
}

function selectIds(selectIds, id) {
	if (selectIds.indexOf(id) != -1)
		return "checked";
	else
		return "unchecked";
}
function buildMsgType(msgType) {
	if ("image" == msgType)
		return "图片";
	else if ("voice" == msgType)
		return "音频";
	else if ("video" == msgType)
		return "视频";
}
</script>