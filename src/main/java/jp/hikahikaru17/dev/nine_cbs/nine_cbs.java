/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hikahikaru17.dev.nine_cbs;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Material;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import net.md_5.bungee.api.chat.TextComponent;

public class nine_cbs extends JavaPlugin implements CommandExecutor{
	public static HashMap<String,Location> deathLocation = new HashMap<>();
	private final static String prefix = ChatColor.DARK_AQUA + "" + "[Nine_CB] " + ChatColor.RESET;
	private final static String prefixError = ChatColor.RED + "" + "[Nine_CB] " + ChatColor.RESET;
	static final String MUST_BE_PLAYER = "プレイヤーから発動する必要があります。";
	static final String TOO_FEW_ARGS = "引数が少なすぎます";
	static final String TOO_MANY_ARGS = "引数が多すぎます";
	private final static String COMMAND_TRIGER = "コマンドブロックの上に立って実行するとコマンドが入ります";
	private final static String RANGE10 = ChatColor.GRAY +"(CBから半径10m以内のプレイヤー全員へ送信)"+ChatColor.RESET;
	private final static String DEFAULT_SELECTER = "@p[r=10]";
	private final static String ALL_SELECTER = "@a[r=10]";
	private final static String VERSION = "1.9";
	private final static String TRIGGER = String.format("%s===%s %s %s===\n", ChatColor.AQUA, ChatColor.LIGHT_PURPLE, COMMAND_TRIGER, ChatColor.AQUA);
	static final String CLAIMED = "保護されています！";
	static final String NOT_ABLE_MODIFY = "この座標を編集する権限がありません。";
	static final String UNKNOWN_GAMEMODE = "不明なゲームモードです。";
	private final static int CBHELP_MAXPAGE = 3;
	public final static boolean DEBUG = true;
	public api API;
	public externalPlugin EP;
	public Logger LOG;

	public void nine_cbs() {
		this.EP = new externalPlugin();
		this.API = new api();
		this.LOG = LOG;
	}
	@Override
	public void onEnable() {
		this.nine_cbs();
		LOG.info("test enable");
		// コマンドを実行するプラグインをこれにするという設定
		getCommand("cbhelp").setExecutor(this);
		getCommand("cbwarp").setExecutor(this);
		getCommand("cbgm").setExecutor(this);
		getCommand("cbtp").setExecutor(this);
		getCommand("cbgive").setExecutor(this);
		getCommand("cbtell").setExecutor(this);
		getCommand("cbsound").setExecutor(this);
		getCommand("cbtitle").setExecutor(this);
		getCommand("cbsubtitle").setExecutor(this);
		getCommand("cbeffect").setExecutor(this);
		getCommand("cbxp").setExecutor(this);
		getCommand("cbspeed").setExecutor(this);
		getCommand("cbmenu").setExecutor(this);
		getCommand("cbgod").setExecutor(this);
		getCommand("cbmenu").setExecutor(this);
		getCommand("cbgod").setExecutor(this);
		getCommand("cbtpt").setExecutor(this);
		getCommand("cbmusic").setExecutor(this);
		getCommand("cbshot").setExecutor(this);
		getCommand("cbtell-a").setExecutor(this);
		getCommand("cbtitle-a").setExecutor(this);
		getCommand("cbsubtitle-a").setExecutor(this);
		getCommand("ninecb").setExecutor(this);
		getCommand("cmb").setExecutor(this);
		getCommand("cmd").setExecutor(this);
		getCommand("uncmb").setExecutor(this);
		getCommand("cbactionbar").setExecutor(this);
		getCommand("cbactionbar-a").setExecutor(this);
		getCommand("cbback").setExecutor(this);
		//this.getServer().getPluginManager().registerEvents(new MyListenerClass(), this));
	}

	@Override
	public void onDisable() {
		super.onDisable(); //LOG.info("test disable");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String cmdname;
		String pa;
		Player player = null;
		boolean isPlayer;
		if (sender instanceof Player) {
			pa = sender.getName();
			player = (Player)sender;
			isPlayer = true;
		} else {
			pa = "#";
			isPlayer = false;
		}
		cmdname = cmd.getName().toLowerCase();
		switch (cmdname) {
			case "ninecb":
				break;
			default:
				break;
		}
		if (cmdname.equalsIgnoreCase("ninecb")){
			String sendmes;
			if (args.length < 1) {
				sendmes(sender,"引数を入力してください！");
				return true;
			}
			switch (args[0].toLowerCase()) {
				case "log":
					showlog(sender);
					return true;
				case "help":
					TextComponent message = new TextComponent("Ping");
					message.setText("Check ping!");
					//message.addExtra(". " + ChatColor.GREEN + "Click to execute a command");
					//message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "ping"));
					//message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Run command: /ping").create()));
					if (player != null) {
						//player.sendMessage(message);
					}
					return true;
				case "about":
					sendmes = "";
					sendmes += "9CB (SALVAGED)\n";
					sendmes += "BY: lolita_is_god\n";
					sendmes += "Discord: @srf78#4256\n";
					sender.sendMessage(sendmes);
					return true;
				case "update":
					sender.sendMessage("");
					return true;
				case "test":
					if (args.length < 2) {
						sendmes(sender,"テストする項目を入力してください！");
						return true;
				}
					switch(args[1].toLowerCase()) {
						/*case "colorbuilder":
							if ((ChatColor.RESET+"a").equals(ChatColorBuilder("&ra"))) {
								sender.sendMessage(prefix + "成功");
								return true;
							} else {
								sender.sendMessage(prefix + "失敗");
								return true;
							}
							//break; */
						default:
							sendmes(sender,"不明なテスト");
							return true;
							//break;

					}
				case "version":
					sendmes(sender,"バージョン: " + VERSION);
					return true;
				case "change":
					break;
				default:
					sendmes(sender,"不明なサブコマンドです。");
					return true;
			}
		} else if (cmdname.equalsIgnoreCase("cbhelp")) {
			if (args.length == 0) {
				String args2[] = new String[1];
				args2[0] = "1";
				args = args2;
			}
			String helpmes = "";
			String lineend = ChatColor.RESET + "\n";
			String linebegin = ChatColor.YELLOW + "";
			switch (args[0]) {
				case "1":
					helpmes += TRIGGER;
					helpmes += String.format("%s/cbwarp <ワープ名>%s", linebegin, lineend);
					helpmes += String.format("%s/cbgm <0|1|2|3>%s", linebegin, lineend);
					helpmes += String.format("%s/cbtp <x> <y> <z>%s", linebegin, lineend);
					helpmes += String.format("%s/cbgive <アイテムID> [量] [データ値] [データタグ]%s", linebegin, lineend);
					helpmes += String.format("%s/cbtell <メッセージ>%s", linebegin, lineend);
					helpmes += String.format("%s/cbsound <音> [x] [y] [z] [音量] [ピッチ] [最小音量]%s", linebegin, lineend);
					helpmes += String.format("%s/cbtitle <メッセージ>%s", linebegin, lineend);
					helpmes += String.format("%s/cbsubtitle <メッセージ>%s", linebegin, lineend);
					//
					helpmes += RETACS(1);
					break;
				case "2":
					helpmes += TRIGGER;
					helpmes += String.format("%s/cbeffect <効果> [秒数] [強さ] [パーティクル表示(true/false)]%s", linebegin, lineend);
					helpmes += String.format("%s/cbxp <量|L <レベル>>%s", linebegin, lineend);
					helpmes += String.format("%s/cbspeed <walk|fly> <0~10>%s", linebegin, lineend);
					helpmes += String.format("%s/cbmenu <メニュー名>%s", linebegin, lineend); //mmopen $1 @p[r=10]
					helpmes += String.format("%s/cbgod <enable|disable>%s", linebegin, lineend);
					helpmes += String.format("%s/cbfly <enable|disable>%s", linebegin, lineend);
					helpmes += String.format("%s/cbtpt <enable|disable>%s", linebegin, lineend);
					helpmes += String.format("%s/cbmusic <曲名>%s", linebegin, lineend);
					//
					helpmes += RETACS(2);
					break;
				case "3":
					helpmes += TRIGGER;
					helpmes += String.format("%s/cbshot <名前>%s", linebegin, lineend);
					helpmes += String.format("%s/cbtell-a <メッセージ>%s%s", linebegin, RANGE10, lineend);
					helpmes += String.format("%s/cbtitle-a <walk|fly> <0~10>%s%s", linebegin, RANGE10, lineend);
					helpmes += String.format("%s/cbsubtitle-a <メニュー名>%s%s", linebegin, RANGE10, lineend);
					helpmes += String.format("%s/cbback <enable|disable>%s", linebegin, lineend);
					helpmes += String.format("%s/cbactionbar <メッセージ>%s", linebegin, lineend);
					helpmes += String.format("%s/cbactionbar-a <enable|disable>%s", linebegin, lineend);
					//
					helpmes += RETACS(3);
					break;
				default:
					sender.sendMessage(prefix + "ページは1～3までしかありません！");
					return true;
			}
			sender.sendMessage(helpmes);
			return true;
		} else if (cmdname.equalsIgnoreCase("cbwarp")) {
			if (enabled("essentials")) {
				setCB(args, 0, 1, sender, String.format("warp %s",args[0]));
			} else {
				errormes(notEnabledPL("essntials"),sender);
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbgm")) {
			if (args.length == 0) {
				errormes(TOO_FEW_ARGS,sender);
				return true;
			}
			switch (args[0]) { // 1.12以降の対策
				case "0":
				case "s":
					args[0] = "survival";
					break;
				case "1":
				case "c":
					args[0] = "creative";
					break;
				case "2":
				case "a":
					args[0] = "adventure";
					break;
				case "3":
				case "sp":
					args[0] = "spectator";
					break;
				default:
					errormes(UNKNOWN_GAMEMODE,sender);
					return true;
			}

			if (DEBUG) {
				sender.sendMessage(prefix + "GAMEMODE: " + args[0]);
			}
			if (isPlayer) {
				if (player == null) {
					sendmes(sender,"NULL: player");
				} else {
					setCB(args, 1, args.length, sender, String.format("minecraft:gamemode %s %s",args[0],DEFAULT_SELECTER));
					return true;
				}
			} else {
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbtp")) {
			setCB(args, 3, 3, sender, String.format("minecraft:tp %s %s %s %s",DEFAULT_SELECTER,args[0],args[1],args[2]));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbgive")) {
			String setCommand = "minecraft:give "+DEFAULT_SELECTER;
			if (args.length < 1) {
				errormes(TOO_FEW_ARGS,sender);
				return true;
			}
			if (isPlayer) {
				if (player == null) {
					sendmes(sender,"NULL: player");
					return true;
				} else {
					for (int i=0;i <= args.length-1;i++) {
						setCommand += (" " + args[i]);
					}
					setCB(args, 1, args.length, sender, setCommand);
					return true;
				}
			} else {
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}
		} else if (cmdname.equalsIgnoreCase("cbtell")) {
			String tellMes = String.join(" ",args); // スペースで切れる対処
			String newTellMes = "";
			if (tellMes.contains("&")){  //装飾コードが含まれていたなら書き換え
				tellMes = ChatColor.translateAlternateColorCodes('&',tellMes);
			}
			setCB(args,1,3,sender,String.format("/minecraft:tell %s %s",DEFAULT_SELECTER,tellMes));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbsound")) {
			String setCommand = "minecraft:playsound $1 $2 $3"; // $1 = src/ $2=master/ $3 = DEFAULT_SELECTER
			if (args.length < 1) {
				errormes(TOO_FEW_ARGS,sender);
				return true;
			}

			if (isPlayer) {
				if (player == null) {
					sendmes(sender,"NULL: player");
					return true;
				} else {
					if (DEBUG) {
						sendmes(sender,String.format("SOUND: %s",args[0]));
					}
					for (int i=1;i <= args.length-1;i++) {
						setCommand += (" " + args[i]);
					}
					LOG.info(setCommand);
					setCommand = setCommand.replace("$1",args[0]);
					setCommand = setCommand.replace("$2","master");
					setCommand = setCommand.replace("$3",DEFAULT_SELECTER);
					setCB(args, 1, args.length, sender, setCommand);
					return true;
				}
			} else {
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}
		} else if (cmdname.equalsIgnoreCase("cbtitle")) {
			setCB(args,0,2,sender,String.format("/minecraft:title %s title %s",DEFAULT_SELECTER,String.join(" ", args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbsubtitle")) {
			setCB(args,0,2,sender,String.format("/minecraft:title %s subtitle %s",DEFAULT_SELECTER,String.join(" ", args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbeffect")) {
			String setCommand = "minecraft:effect "+DEFAULT_SELECTER;
			if (args.length < 1) {
				errormes(TOO_FEW_ARGS,sender);
				return true;
			}
			if (isPlayer) {
				if (player == null) {
					sendmes(sender,"NULL: player");
					return true;
				} else {
					for (int i=0;i <= args.length-1;i++) {
						setCommand += (" " + args[i]);
					}
					setCB(args,0,3,sender,setCommand);
					//changesCB(player.getLocation(), setCommand.trim(),player.getName());
					return true;
				}
			} else {
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}
		} else if (cmdname.equalsIgnoreCase("cbxp")) {
			setCB(args,0,2,sender,String.format("/minecraft:xp %s %s",args[0],DEFAULT_SELECTER));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbspeed")) {
			setCB(args,0,3,sender,String.format("/speed %s %s",args[0],args[1]));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbmenu")) {
			if (getServer().getPluginManager().isPluginEnabled("mymenu")) {
				setCB(args,0,2,sender,String.format("/mmopen %s %s",args[0],DEFAULT_SELECTER));
			} else {
				sendmes(sender, notEnabledPL("mymenu"));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbgod") || cmdname.equalsIgnoreCase("cbfly") || cmdname.equalsIgnoreCase("cbtpt")) {
			if (enabled("essentials")) {
				if ("enable".equals(args[0].toLowerCase()) || "disable".equals(args[0].toLowerCase())) {
					setCB(args,0,2,sender,String.format("/essentials:%s %s",cmdname.toLowerCase(),args[0]));
				} else {
					errormes("第一引数はenableかdisableにしてください。",sender);
				}
			} else {
				sendmes(sender,notEnabledPL("essentials"));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbmusic")) {
			if (enabled("icJukeBox")){
				setCB(args,0,2,sender,String.format("/music play %s %s",args[0],DEFAULT_SELECTER));
			} else {
				errormes(notEnabledPL("icJukeBox"),sender);
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbshot")) {
			setCB(args,0,2,sender,String.format("/shot give %s %s",DEFAULT_SELECTER,args[0]));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbtell-a")) {
			setCB(args,0,2,sender,String.format("/minecraft:tell %s %s",ALL_SELECTER,String.join(" ",args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbtitle-a")) {
			setCB(args,0,2,sender,String.format("/minecraft:title %s title %s",ALL_SELECTER,String.join(" ",args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbsubtitle-a")) {
			setCB(args,0,2,sender,String.format("/minecraft:title %s subtitle %s",DEFAULT_SELECTER,String.join(" ",args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cmd")) {
			sendmes(sender,"/cmbと打ってください。");
			return true;
		} else if (cmdname.equalsIgnoreCase("cmb")) {
			if (player==null) {
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}
			//WORLD GUARD CHECK
			if (DEBUG) {
				LOG.info(String.format("%3.2f,%d",player.getLocation().getY(),player.getLocation().getBlockY()));
			}
			if (API.canBuild(player.getLocation(), player)) {
				player.getLocation().getBlock().setType(Material.COMMAND);
				EP.getCoreProtect().logPlacement(player.getName(), player.getLocation(), Material.COMMAND, (byte)0); //CORE PROTECT
			} else {
				errormes(NOT_ABLE_MODIFY,sender);
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("uncmd")) {
			sendmes(sender,"/uncmbと打ってください。");
			return true;
		} else if (cmdname.equalsIgnoreCase("uncmb")) {
			getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:tp "+ pa +"~ ~-1 ~");
			if (player == null) {
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}

			if (API.canBuild(player.getLocation(), player)) {
				player.getLocation().getBlock().setType(Material.AIR);
				EP.getCoreProtect().logRemoval(player.getName(), player.getLocation(), Material.COMMAND, (byte)0); 			//CORE PROTECT
			} else {
				errormes(NOT_ABLE_MODIFY,sender);
			}
			return true;



		} else if (cmdname.equalsIgnoreCase("cbwarp")) {
			if (enabled("essentials")) {
				setCB(args,0,2,sender,String.format("/warp %s %s",args[0],DEFAULT_SELECTER));
			} else {
				sendmes(sender,notEnabledPL("essentials"));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbactionbar")) {
			setCB(args,0,2,sender,String.format("minecraft:title %s actionbar %s",DEFAULT_SELECTER,String.join(" ",args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbactionbar-a")) {
			setCB(args,0,2,sender,String.format("minecraft:title %s actionbar %s",ALL_SELECTER,String.join(" ",args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbback")) {
			sender.sendMessage("未サポート");//setCB(args,0,2,sender,"/nine_cbs:back");
			return true;
		} else if (cmdname.equalsIgnoreCase("back")) {
			sender.sendMessage("未サポート");
			return true;
		}
		/**/
		return false;//該当コマンドなし
	}

	/**
	 *
	 * @param args
	 */

	private void setCB(String args[], int toofew, int toomany, CommandSender sender, String command){
		API.setCB(args, toofew, toomany, sender, command);
	}

	private String getsCB(Location blockLocate) {
		if (blockLocate.getBlock().getType() != Material.COMMAND) {
			return "";
		}
		CommandBlock cb = (CommandBlock)blockLocate.getBlock().getState();
		return cb.getCommand();
	}

	private static String RETACS(int page){
		return ChatColor.AQUA+"=== "+ChatColor.DARK_GREEN+ChatColor.BOLD+"cbhelp "+ChatColor.GOLD+"-- "+ChatColor.GREEN+ChatColor.BOLD+"ページ "+ChatColor.RED+String.format("%d",page)+ChatColor.GOLD+"/"+ChatColor.RED+String.format("%d ",CBHELP_MAXPAGE)+ChatColor.DARK_AQUA+"("+ChatColor.RESET+ChatColor.UNDERLINE+"/cbhelp 2"+ChatColor.RESET+" で次のページへ"+ChatColor.DARK_AQUA+") "+ChatColor.AQUA+" ===";
	}

	private void changesCB(Location blockLocate, String command, String playerName) {
		API.changeCB(blockLocate, command, playerName);
	}

	private void logging(Location blockLocate, String action, String desc, String player) {
		API.log(blockLocate, action, desc, player);
	}
	private boolean enabled(String plname) {
		return getServer().getPluginManager().getPlugin(plname) != null;
	}

	static void sendmes(CommandSender sender, String mes) {
		sender.sendMessage(prefix + mes);
	}

	private String notEnabledPL(String plname) {
		return String.format("%s は有効化されていないようです。\n管理者へお問い合わせください。",plname);
	}

	private void tellRaw(Player player, String str) {
		// nop
	}

	private void executeCommand(String cmd) {
		getServer().dispatchCommand(getServer().getConsoleSender(), cmd);
	}
	/** <!-- javadoc -->
	 * @param mes ストライク！！！！
	 * @deprecated you can use ChatColor.translateAlternateColorCodes('&',mes)
	 */

	private static String ChatColorBuilder(String mes){ //mes is like "&8&m cake is a lie"
		return ChatColor.translateAlternateColorCodes('&',mes);
	}
	private void NOP() {
		// No Operation
	}

	/*********************************************************************************************
	 *      文字列の指定された位置から、指定された文字数分の文字列を返します。
	 *
	 * @param   stTarget    取り出す元になる文字列。
	 * @param   iStart      取り出しを開始する位置。
	 * @param   iLength     取り出す文字数。
	 * @return	      指定された位置から指定された文字数分の文字列。
	 *		      文字数を超えた場合は、指定された位置以降のすべての文字列が返されます。
	 *********************************************************************************************/
	public static final String mid(final String stTarget, final int iStart, final int iLength) {
		if (iStart + iLength < stTarget.length()) { // スタート位置+長さ < 文字列の長さ
			return "";
		}
		return stTarget.substring(iStart, iStart + iLength);
	}

	/** public static final void sendunavaiable(CommandSender sendto) {
		sendto.SendMessage(prefix + " 未実装");
	}*/

	private void errormes(String s, CommandSender cs) {
		cs.sendMessage(prefixError + s);
	}

	private void showlog(CommandSender sender) {
		if (!API.isPlayer(sender)) {
			sender.sendMessage(MUST_BE_PLAYER);
			return;
		}
		Player player = (Player)sender;
		final Location locate = player.getLocation();
		final String BASE_PATH = "plugins/ninecb/blocklog/";
		final String FILE_NAME = String.format("%d_%d_%d.yml",locate.getBlockX(),(int)(locate.getY()-1),(int)locate.getZ());
		LOG.info(FILE_NAME);
		final File f = new File(BASE_PATH + FILE_NAME);
		YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
		String m = "";
		double timesago;
		int i;
		for (i=0;i <= 999999;i ++) {
			if (yamlFile.get(String.format("log%d",i)) == null) {break;}
		}
		m += "----"+ " Nine_cb/LOGS " + "----\n";
		Long nowTime = System.currentTimeMillis();
		for (int j=i-1;j >= 0;j -= 1) {
			Long beforeTime = Long.parseLong(yamlFile.getString(String.format("log%d.time", j)));
			//LOG.info(String.format("[N,B,N-B]%d - %d = %d",nowTime,beforeTime,nowTime-beforeTime));
			timesago = (double)((nowTime - beforeTime) / (1000*60*60)); //時間単位
			//LOG.info(yamlFile.getString(String.format("log%d.time", j)));
			String l = "";
			l += String.format("%.2f",((nowTime-beforeTime)/(60*60*1000d)));
			LOG.info(l);
			m += l; // time
			m += "/h ago : ";
			m += yamlFile.getString(String.format("log%d.player", j)); // user
			m += " ";
			m += yamlFile.getString(String.format("log%d.action", j)); // action
			m += " ";
			m += yamlFile.getString(String.format("log%d.after",j)); //command
			m += "\n";
		}
		LOG.info(m);
		sender.sendMessage(m);
	}
}

