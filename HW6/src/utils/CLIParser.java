package utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * A command line parser.
 */
public class CLIParser {
    List <String> args = new ArrayList<>();
    HashMap<String, List<String>> map = new HashMap<>();
    Set<String> flags = new HashSet<>();

    public CLIParser(String arguments[])
    {
        this.args = Arrays.asList(arguments);
        map();
    }

    /**
     * Parse the arguments and put them in a map.
     * 
     * @return the map
     */
    public Set<String> getKeys()
    {
        Set<String> argumentNames = new HashSet<>();
        argumentNames.addAll(flags);
        argumentNames.addAll(map.keySet());

        return argumentNames;
    }
    
    /**
     * Check if flag is given
     * 
     * @param flag the flag name
     * @return true if flag is given
     */
    public boolean getFlag(String flagName)
    {
        return flags.contains(flagName);
    }

    /**
     * Return argument value for particular argument name
     * 
     * @param argumentName the argument name
     * @return the argument value if it exists, null otherwise
     */
    public String getVal(String argumentName)
    {
        return map.containsKey(argumentName) ? 
            map.get(argumentName).get(0) :
            null;
    }

    /**
     * Map the flags and argument names with the values 
     */
    public void map()
    {
        for (String arg: args) {
            if (arg.startsWith("-")) {
                if (args.indexOf(arg) == (args.size() - 1)) {
                    flags.add(arg.replace("-", ""));

                } else if (args.get(args.indexOf(arg)+1).startsWith("-")) {
                    flags.add(arg.replace("-", ""));

                } else {
                    //List of values (can be multiple)
                    List<String> argumentValues = new ArrayList<>();
                    int i = 1;
                    while (args.indexOf(arg)+i != args.size() && !args.get(args.indexOf(arg)+i).startsWith("-")) {
                        argumentValues.add(args.get(args.indexOf(arg)+i));
                        i++;
                    }

                    map.put(
                        arg.replace("-", ""),
                        argumentValues
                    );
                }
            }
        }
    }

    public void print()
    {
        System.out.println("Flags: " + flags);
        System.out.println("Arguments: " + map);
    }
}