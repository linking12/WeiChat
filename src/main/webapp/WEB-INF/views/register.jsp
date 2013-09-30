<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>用户注册</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submitbtn").click(function() {
			$('#registerForm').attr('action', '${ctx}/register/submit');
			$('#registerForm').submit();
		});
	});
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="57" background="${images}/tiao.jpg"><table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><img src="${images}/logo.png" width="125" height="32" /></td>
        <td><div align="right" class="di"><a href="#" class="b"></a></div></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
    <form:form id="registerForm" method="post" modelAttribute="registerForm">
      <tr>
        <td height="20">&nbsp;</td>
      </tr>
      <tr>
        <td height="555" valign="top"><table width="942" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="53" background="${images}/shan.png"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="2%">&nbsp;</td>
                <td width="5%" class="biao"><img src="${images}/ic_2.png" width="33" height="25" /></td>
                <td width="93%" class="biao">用户注册</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="280" valign="middle" background="${images}/he.png"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="63%"><table width="90%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="19%"><div align="right" class="biao">手机号</div></td>
                    <td width="4%">:</td>
                    <td width="77%"><form:input path="phone" class="full" /></td>
                  </tr>
                  <tr>
                    <td height="10"><div align="right"></div></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td><div align="right" class="biao">用户名</div></td>
                    <td>:</td>
                    <td><form:input path="name" class="full" /></td>
                  </tr>
                  <tr>
                    <td><div align="right" class="biao">昵称</div></td>
                    <td>:</td>
                    <td><form:input path="nickName" class="full" /></td>
                  </tr>
                  <tr>
                    <td height="10"><div align="right"></div></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td><div align="right" class="biao">密码</div></td>
                    <td>:</td>
                    <td><form:password path="spassword1" class="full" /></td>
                  </tr>
                  <tr>
                    <td height="10"><div align="right"></div></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td><div align="right" class="biao">确认密码</div></td>
                    <td>:</td>
                    <td><form:password path="spassword2" class="full" /></td>
                  </tr>
                  <tr>
                    <td height="10">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="3"><div align="left">
                      <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="6%"><label>
                            <input type="radio" name="radiobutton" value="radiobutton" />
                          </label></td>
                          <td width="61%"><span class="zeng">我了解.....</span> </td>
                          <td width="33%"><div align="right"><input type="image" name="submit" id="submitbtn" src="${images}/bt1_n.jpg" width="76" height="40" /></div></td>
                        </tr>
                      </table>
                    </div></td>
                    </tr>
                </table>
                  </td>
                <td width="2%"><img src="${images}/su.png" width="1" height="257" /></td>
                <td width="35%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="20">&nbsp;</td>
                  </tr>
                  <tr>
                    <td><span class="biao">已有用户</span> <a href="${ctx }/login" class="a">登录</a></td>
                  </tr>
                   <tr hight="20"><td>&nbsp;</td></tr>
                  	 <tr hight="20"><td><form:errors path="phone" cssClass="error" /></td></tr>
                  	 <tr hight="20"><td><form:errors path="name" cssClass="error" /></td></tr>
                  	  <tr hight="20"><td><form:errors path="nickName" cssClass="error" /></td></tr>
                  	 <tr hight="20"><td><form:errors path="spassword1" cssClass="error" /></td></tr>
                  	 <tr hight="20"><td><form:errors path="spassword2" cssClass="error" /></td></tr>
                     <tr hight="20"><td><span class="error">${errorMsg}</span></td></tr>
                </form:form>
                </table></td>
              </tr>
              
            </table></td>
          </tr>
          <tr>
            <td><img src="${images}/xia_zu.png" width="942" height="14" /></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>