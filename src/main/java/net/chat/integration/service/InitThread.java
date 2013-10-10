package net.chat.integration.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.chat.integration.vo.WxCmd;
import net.chat.integration.vo.WxContent;
import net.chat.service.impl.IntegrationServiceImpl;
import net.chat.utils.DbUtil;

public class InitThread extends Thread {
	private boolean flag = true;

	public InitThread() {
	}

	public void run() {
		while (flag) {
			IntegrationServiceImpl.logs("-------Weixin init Config-------");
			IntegrationServiceImpl.souces.clear();
			IntegrationServiceImpl.acounts.clear();
			IntegrationServiceImpl.games.clear();
			IntegrationServiceImpl.urlseqs.clear();
			IntegrationServiceImpl.autoreplay_cmd.clear();
			initconfig();
			IntegrationServiceImpl.logs("-------Init end-------");
			try {
				sleep(5 * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void initconfig() {
		ResultSet rs = null;
		DbUtil db = new DbUtil();
		String sql = "";
		sql = "select a.url,m.msgtype,m.id,a.seq,m.sourceid,m.action from wx_account as a,wx_msgtype as m where a.istatus=0 and a.id=m.accountid;";

		try {
			rs = db.getResultSet(sql);
			while (rs.next()) {

				IntegrationServiceImpl.logs(rs.getString("url")
						+ rs.getString("msgtype") + " =  "
						+ rs.getString("sourceid"));
				if ("program".equals(rs.getString("action"))) {
					IntegrationServiceImpl.acounts.put(
							trim(rs.getString("url"))
									+ trim(rs.getString("msgtype")),
							rs.getString("sourceid") + "_program");
				} else {
					IntegrationServiceImpl.acounts.put(
							trim(rs.getString("url"))
									+ trim(rs.getString("msgtype")),
							rs.getString("sourceid"));
				}

				IntegrationServiceImpl.urlseqs.put(rs.getString("url"),
						rs.getString("seq"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.connClose();
		}

		sql = "select c.*,a.url from wx_cmd as c,wx_account as a where a.istatus=0 and a.id=c.accountid;";

		try {
			rs = db.getResultSet(sql);
			while (rs.next()) {
				WxCmd cmd = new WxCmd();
				cmd.setAccountid(rs.getString("accountid"));
				cmd.setCmd(rs.getString("cmd"));
				cmd.setId(rs.getString("id"));
				cmd.setMessageid(rs.getString("messageid"));
				IntegrationServiceImpl.autoreplay_cmd.put(
						trim(rs.getString("url")) + rs.getString("cmd"), cmd);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.connClose();
		}
		// /////////////////////////
		sql = "select m.id,m.url from wx_game as m,wx_msgtype as c  where c.accountid in (select id from wx_account where istatus =0) and c.action='program'  and m.id=c.sourceid;";

		try {
			rs = db.getResultSet(sql);
			while (rs.next()) {
				IntegrationServiceImpl.logs(rs.getString("id") + ":"
						+ rs.getString("url"));
				IntegrationServiceImpl.games.put(rs.getString("id"),
						trim(rs.getString("url")));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.connClose();
		}

		sql = "select * from wx_message where accountid in (select id from wx_account where istatus =0);";
		try {
			rs = db.getResultSet(sql);
			while (rs.next()) {
				if ("text".equals(rs.getString("msgtype"))) {
					IntegrationServiceImpl.souces.put(rs.getString("id"),
							RespBuilder.buildText(rs.getString("content")));
				} else if ("music".equals(rs.getString("msgtype"))) {
					IntegrationServiceImpl.souces.put(rs.getString("id"),
							loadMusic(rs.getString("id")));
				} else if ("multimedia".equals(rs.getString("msgtype"))) {
					IntegrationServiceImpl.souces.put(rs.getString("id"),
							loadMulti(rs.getString("id")));
				}

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.connClose();
		}

	}

	public String loadMulti(String msgid) {
		String tt = "";
		List<WxContent> contents = new ArrayList<WxContent>();
		ResultSet rs = null;
		DbUtil db = new DbUtil();
		String sql = "select * from wx_content where messageid=" + msgid;
		try {

			rs = db.getResultSet(sql);

			while (rs.next()) {

				WxContent cnt = new WxContent();
				cnt.setTitle(rs.getString("title"));
				cnt.setPicUrl(rs.getString("picurl"));
				cnt.setUrl(rs.getString("url"));
				cnt.setDescription(rs.getString("description"));
				contents.add(cnt);

			}

			tt = RespBuilder.bulidPicText(contents);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.connClose();
		}
		contents.clear();
		return tt;
	}

	public String loadMusic(String msgid) {
		String tt = "";
		ResultSet rs = null;
		DbUtil db = new DbUtil();
		String sql = "select * from wx_content where messageid=" + msgid;
		try {
			rs = db.getResultSet(sql);

			while (rs.next()) {

				tt = RespBuilder.buildMusic(rs.getString("title"),
						rs.getString("description"), rs.getString("musicurl"),
						rs.getString("hqmusicurl"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.connClose();
		}
		return tt;
	}

	public void destroy() {
		flag = false;
		super.destroy();
		this.interrupt();
	}

	public String trim(String str) {
		if (str != null) {
			str = str.trim();
		}
		return str;
	}

}
