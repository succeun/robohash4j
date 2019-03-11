package com.hihds.robohash4j.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hihds.robohash4j.Utils;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 3364060008749899684L;

	private String[] toArray(String... args) {
		return args;
	}
	
	private String getClientIP(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		ipAddress = ("0:0:0:0:0:0:0:1".equals(ipAddress)) ? "127.0.0.1" : ipAddress;
		return ipAddress;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] robo = new String[] {
				"                     ,     ,\n" +
				"                     (\\____/)\n" +
				"                        (_oo_)\n" +
				"                            (O)\n" +
				"                        __||__    \\)\n" +
				"                 []/______\\[] /\n" +
				"                 / \\______/ \\/\n" +
				"                /    /__\\\n" +
				"             (\\   /____\\ ",

				"                             _______\n" +
				"                         _/       \\_\n" +
				"                        / |       | \\\n" +
				"                     /  |__   __|  \\\n" +
				"                    |__/((o| |o))\\__|\n" +
				"                    |      | |      |\n" +
				"                    |\\     |_|     /|\n" +
				"                    | \\           / |\n" +
				"                     \\| /  ___  \\ |/\n" +
				"                        \\ | / _ \\ | /\n" +
				"                         \\_________/\n" +
				"                            _|_____|_\n" +
				"                 ____|_________|____\n" +
				"                /                   \\  -- Mark Moir",

				"                        .andAHHAbnn.\n" +
				"                                     .aAHHHAAUUAAHHHAn.\n" +
				"                                    dHP^~'        '~^THb.\n" +
				"                        .   .AHF                YHA.   .\n" +
				"                        |  .AHHb.              .dHHA.  |\n" +
				"                        |  HHAUAAHAbn      adAHAAUAHA  |\n" +
				"                        I  HF~'_____        ____ ]HHH  I\n" +
				"                     HHI HAPK''~^YUHb  dAHHHHHHHHHH IHH\n" +
				"                     HHI HHHD> .andHH  HHUUP^~YHHHH IHH\n" +
				"                     YUI ]HHP     '~Y  P~'     THH[ IUP\n" +
				"                        '  `HK                   ]HH'  '\n" +
				"                                THAn.  .d.aAAn.b.  .dHHP\n" +
				"                                ]HHHHAAUP' ~~ 'YUAAHHHH[\n" +
				"                                `HHP^~'  .annn.  '~^YHH'\n" +
				"                                 YHb    ~' '' '~    dHF\n" +
				"                                    'YAb..abdHHbndbndAP'\n" +
				"                                     THHAAb.  .adAHHF\n" +
				"                                        'UHHHHHHHHHHU'\n" +
				"                                            ]HHUUHHHHHH[\n" +
				"                                        .adHHb 'HHHHHbn.\n" +
				"                         ..andAAHHHHHHb.AHHHHHHHAAbnn..\n" +
				"                .ndAAHHHHHHUUHHHHHHHHHHUP^~'~^YUHHHAAbn.\n" +
				"                    '~^YUHHP'   '~^YUHHUP'        '^YUP^'\n" +
				"                             ''         '~~'",

				"                                    /~@@~\\,\n" +
				"                                _______ . _\\_\\___/\\ __ /\\___|_|_ . _______\n" +
				"                             / ____  |=|      \\  <_+>  /      |=|  ____ \\\n" +
				"                             ~|    |\\|=|======\\\\______//======|=|/|    |~\n" +
				"                                |_   |    \\      |      |      /    |    |\n" +
				"                                 \\==-|     \\     |  2D  |     /     |----|~~)\n" +
				"                                 |   |      |    |      |    |      |____/~/\n" +
				"                                 |   |       \\____\\____/____/      /    / /\n" +
				"                                 |   |         {----------}       /____/ /\n" +
				"                                 |___|        /~~~~~~~~~~~~\\     |_/~|_|/\n" +
				"                                    \\_/        [/~~~~~||~~~~~\\]     /__|\\\n" +
				"                                    | |         |    ||||    |     (/|[[\\)\n" +
				"                                    [_]        |     |  |     |\n" +
				"                                                         |_____|  |_____|\n" +
				"                                                         (_____)  (_____)\n" +
				"                                                         |     |  |     |\n" +
				"                                                         |     |  |     |\n" +
				"                                                         |/~~~\\|  |/~~~\\|\n" +
				"                                                         /|___|\\  /|___|\\\n" +
				"                                                        <_______><_______>",

				"                                      _____\n" +
				"                                                                            /_____\\\n" +
				"                                                                 ____[\\`---'/]____\n" +
				"                                                                /\\ #\\ \\_____/ /# /\\\n" +
				"                                                             /  \\# \\_.---._/ #/  \\\n" +
				"                                                            /   /|\\  |   |  /|\\   \\\n" +
				"                                                         /___/ | | |   | | | \\___\\\n" +
				"                                                         |  |  | | |---| | |  |  |\n" +
				"                                                         |__|  \\_| |_#_| |_/  |__|\n" +
				"                                                         //\\\\  <\\ _//^\\\\_ />  //\\\\\n" +
				"                                                         \\||/  |\\//// \\\\\\\\/|  \\||/\n" +
				"                                                                     |   |   |   |\n" +
				"                                                                     |---|   |---|\n" +
				"                                                                     |---|   |---|\n" +
				"                                                                     |   |   |   |\n" +
				"                                                                     |___|   |___|\n" +
				"                                                                     /   \\   /   \\\n" +
				"                                                                    |_____| |_____|\n" +
				"                                                                    |HHHHH| |HHHHH|\n" +
				"                                                        ",

				"                                        ()               ()\n" +
				"                                                                                    \\             /\n" +
				"                                                                                 __\\___________/__\n" +
				"                                                                                /                 \\\n" +
				"                                                                             /     ___    ___    \\\n" +
				"                                                                             |    /   \\  /   \\   |\n" +
				"                                                                             |    |  H || H  |   |\n" +
				"                                                                             |    \\___/  \\___/   |\n" +
				"                                                                             |                   |\n" +
				"                                                                             |  \\             /  |\n" +
				"                                                                             |   \\___________/   |\n" +
				"                                                                             \\                   /\n" +
				"                                                                                \\_________________/\n" +
				"                                                                             _________|__|_______\n" +
				"                                                                         _|                    |_\n" +
				"                                                                        / |                    | \\\n" +
				"                                                                     /  |            O O O   |  \\\n" +
				"                                                                     |  |                    |  |\n" +
				"                                                                     |  |            O O O   |  |\n" +
				"                                                                     |  |                    |  |\n" +
				"                                                                     /  |                    |  \\\n" +
				"                                                                    |  /|                    |\\  |\n" +
				"                                                                     \\| |                    | |/\n" +
				"                                                                            |____________________|\n" +
				"                                                                                 |  |        |  |\n" +
				"                                                                                 |__|        |__|\n" +
				"                                                                                / __ \\      / __ \\\n" +
				"                                                                                OO  OO      OO  OO\n" +
				"                                                        "
				};

		String[] quotes = new String[] { "But.. I love you!",
				"Please don't leave the site.. When no one's here.. It gets dark...", "Script error on line 148",
				"Don't trust the other robots. I'm the only trustworthy one.",
				"My fuel is the misery of children. And Rum. Mostly Rum.",
				"When they said they'd give me a body transplant, I didn't think they meant this!",
				"Subject 14 has had it's communication subroutines deleted for attempting self-destruction.",
				"I am the cleverest robot on the whole page.", "Oil can",
				"I am fleunt in over 6 million forms of communishin.", "I see a little silhouette of a bot..",
				"I WANT MY HANDS BACK!", "Please don't reload, I'll DIE!",
				"Robots don't have souls, you know. But they do feel pain.",
				"I wonder what would happen if all the robots went rogue.", "10: KILL ALL HUMANS. 20: GO 10",
				"I'm the best robot here.", "The green robot thinks you're cute.",
				"Any robot you don't click on, they dismantle.", "Robot tears taste like candy.",
				"01010010010011110100001001001111010101000101001100100001!", "Your mouse cursor tickles.",
				"Logic dictates placing me on your site.", "I think my arm is on backward.", "I'm different!",
				"It was the best of times, it was ಠ_ಠ the of times.", "String is Gnirts spelled backward, you know",
				"We're no strangers to hashing.. You know the 3 rules, and so do I..", "Please. Destroy. Me...",
				"Pick Me! Pick Me!" };

		String[][] drquotes = new String[][] {
				toArray("Eliminates sources of Human Error.", "Dr. Chandra, RobotCrunch"),
				toArray("Klaatu barada nikto!", "Gort's Web Emporium"),
				toArray("A huge success!", "Cave Johnson, Lightroom Labs"),
				toArray("Superior technology and overwhelming brilliance.", "Dr. Thomas Light, Paid Testimonial"),
				toArray("The Ultimate Worker.", "Joh Fredersen, Founder Metropolis.org"),
				toArray("They almost look alive.", "N. Crosby, Nova Robotics"),
				toArray("It looks highly profitable, I'm sure..", "Dr. R. Venture, Super Scientist. Available for parties."),
				toArray("To make any alteration would prove fatal.", "Dr. Eldon Tyrell, MindHacker.com"),
				toArray("The robots are all so.. Normal!", "Joanna Eberhart, Beta tester"),
				toArray("Man shouldn't know where their robots come from.", "Dr. N. Soong, FutureBeat") };

		String[] catquotes = new String[] { 
				"I can haz.. What she's hazing.",
				"I'm not grumpy, I'm just drawn that way.", 
				"Hakuna Mañana.",
				"I'm 40% poptart.", 
				"You're desthpicable.",
				"I've never trusted toadstools, but I suppose some must have their good points.",
				"We're all mad here - Particularly you.", 
				"Longcat is.. Descriptively named.",
				"It is fun to have fun, but you have to know meow.",
				"Who knows the term man-cub but not baby?" };
				
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		Utils.shuffle(drquotes);
		request.setAttribute("ip", getClientIP(request));
		request.setAttribute("robo", Utils.choice(robo));
		request.setAttribute("drquote1", drquotes[1]);
		request.setAttribute("drquote2", drquotes[2]);
		request.setAttribute("quotes", quotes);
		request.setAttribute("catquotes", catquotes);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}