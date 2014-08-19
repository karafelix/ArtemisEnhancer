import java.io.IOException;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public class Launcher
{
	public static void main(String[] args) throws IOException
	{
		OptionParser parser = new OptionParser();
		OptionSpec<Integer> port = parser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(2010);
		OptionSpec<String> host = parser.accepts("host").withRequiredArg().ofType(String.class).required();
		parser.accepts("proxy");

		OptionSet options = parser.parse(args);

		if (options.has("proxy"))
			new Thread(new HelmProxy(options.valueOf(host), options.valueOf(port), options.valueOf(port))).start();
		else
			new HelmController(options.valueOf(host), options.valueOf(port));
	}
}