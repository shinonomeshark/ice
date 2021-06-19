package xyz.hackage.rewritten.gui;

import java.awt.Color;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.login.GuiAltLogin;
import xyz.hackage.rewritten.modules.render.HudModule;
import xyz.hackage.rewritten.modules.settings.ModeSetting;
import xyz.hackage.rewritten.util.GuiUtil;
import xyz.hackage.rewritten.util.RainbowUtil;
import xyz.hackage.rewritten.util.SSLUtilities;

public class GuiLogin extends GuiScreen {
	public GuiLogin () {		}
	
	
	String thingText = "Awaiting user input...";
	String uidToSend = "0000";
	boolean focused = true;
	String textInBox = "";
	
	public static String getHTML(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      try (BufferedReader reader = new BufferedReader(
	                  new InputStreamReader(conn.getInputStream()))) {
	          for (String line; (line = reader.readLine()) != null; ) {
	              result.append(line);
	          }
	      }
	      return result.toString();
	   }
	
	public void initGui() {
		ScaledResolution sr = new ScaledResolution(mc);
		this.buttonList.add(new GuiButton(1, sr.getScaledWidth()/2-70, sr.getScaledHeight()/2+30, 140, 20, "Login"));
		
		
		
		System.out.println("init");
	}
	
	 private static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	      MessageDigest md = MessageDigest.getInstance("SHA-1");
	      byte[] sha1hash = new byte[40];
	      md.update(text.getBytes("iso-8859-1"), 0, text.length());
	      sha1hash = md.digest();
	      return convertToHex(sha1hash);
	   }

	   private static String convertToHex(byte[] data) {
	      StringBuffer buf = new StringBuffer();

	      for(int i = 0; i < data.length; ++i) {
	         int halfbyte = data[i] >>> 4 & 15;
	         int var4 = 0;

	         do {
	            if (halfbyte >= 0 && halfbyte <= 9) {
	               buf.append((char)(48 + halfbyte));
	            } else {
	               buf.append((char)(97 + (halfbyte - 10)));
	            }

	            halfbyte = data[i] & 15;
	         } while(var4++ < 1);
	      }

	      return buf.toString();
	   }
	
	public static String getHwid() throws Exception {
	      String hwid = SHA1(System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("COMPUTERNAME") + System.getProperty("user.name"));
	      StringSelection stringSelection = new StringSelection(hwid);
	      return hwid;
	   }
	
	public void HandleSumSingWong() {


		Thread t = new Thread() {
			public void run() {
				try {
					
					SSLUtilities.trustAllHostnames();
					SSLUtilities.trustAllHttpsCertificates();
					
					thingText = "Connecting...";
					thingText = getHTML("https://admin.sakamoto.games/verif?uid=" + textInBox + "&hwid=" + getHwid());
					
					Thread.sleep(3000l);
					if(thingText.startsWith("Welcome, ")) {
						mc.displayGuiScreen(new BetterMainMenu());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	public void actionPerformed(GuiButton button) {
		if(button.id == 1) {
			HandleSumSingWong();
		}
	}
	
	
	public void keyTyped(char typedChar, int keyCode) {
		if(focused) {
			if(keyCode == Keyboard.KEY_BACK) {
				if(textInBox != "" && textInBox.length() != 0) {
					textInBox = textInBox.substring(0, textInBox.length() -1);
				}
			} else if(keyCode == Keyboard.KEY_RETURN) {
				HandleSumSingWong();
			} else {
				if(typedChar == '0' || typedChar == '1' || typedChar == '2' || typedChar == '3' || typedChar == '4' || typedChar == '5' || typedChar == '6' || typedChar == '7' || typedChar == '8' || typedChar == '9') {
					if(textInBox.length() < 4)
						textInBox = textInBox + typedChar;
				}
			}
		}
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		
//		System.out.println(textInBox);
		
		this.mc.getTextureManager().bindTexture(new ResourceLocation("hackage/icons/blur.png"));
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());

		GuiUtil.renderRoundedQuad(new Vec3(((sr.getScaledWidth())/2)-100, (sr.getScaledHeight()/2)-80, 0), new Vec3(((sr.getScaledWidth())/2)+100, (sr.getScaledHeight()/2)+80, 0), 5, new Color(0x80000000, true));
		Client.sbufr.drawCenteredStringWithShadow("Sentinel " + Client.VERSION, sr.getScaledWidth()/2, sr.getScaledHeight()/2-80, -1);
		
		Client.sufr.drawStringWithShadow("UID", sr.getScaledWidth()/2-80, sr.getScaledHeight()/2-20, -1);
		this.drawRect(sr.getScaledWidth()/2-80, sr.getScaledHeight()/2-10, sr.getScaledWidth()/2+80, sr.getScaledHeight()/2+10, 0xaa000000);
//		this.drawRect(sr.getScaledWidth()/2-80, sr.getScaledHeight()/2+12, sr.getScaledWidth()/2+80, sr.getScaledHeight()/2+10, 0xcc000000);
		
		for(int i = 0; i < 160;) {
			this.drawRect(sr.getScaledWidth()/2-79+i, sr.getScaledHeight()/2+11, sr.getScaledWidth()/2-80+i, sr.getScaledHeight()/2+10, RainbowUtil.SkyRainbow(i/10f, 1f, 0.6f));
			i++;
		}
		
		if(!focused) {
			if(textInBox != "")
				Client.ufr.drawString("1234", sr.getScaledWidth()/2-75, sr.getScaledHeight()/2-6.7f, 0x77ffffff);
		} else {
			Client.ufr.drawString(textInBox + "_", sr.getScaledWidth()/2-75, sr.getScaledHeight()/2-6.7f, -1);
		}
		Client.sufr.drawCenteredStringWithShadow(thingText, sr.getScaledWidth()/2, sr.getScaledHeight()/2+15, -1);
		
//		GuiUtil.drawCircle(0, 0, 10, 1, Color.BLACK, false);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
	}
}
