package com.sap.yupgradeinfoext;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */

/**
 * @author I314327
 *
 */
public class UpgradeInfoExtractor
{
	final static Map<String, String> versionMap = new HashMap<>();

	private static String fileName = null;

	public static void main(final String[] args) throws IOException
	{
		if (args != null && args.length > 0)
		{
			fileName = args[0];
		}
		else
		{
			fileName = "C:\\Ankit\\coronab2b_S7\\hybris";
		}

		System.out.println(fileName);


		final File file = new File(fileName + "\\bin\\custom\\");

		if (file.isDirectory())
		{
			printBuildNumberFile(file.listFiles());
		}


		final File platformFile = new File(fileName + "\\bin\\platform\\");

		if (platformFile.isDirectory())
		{
			printBuildNumberFile(platformFile.listFiles());
		}

		//System.out.println("versionMap=" + versionMap);


		//System.out.println(getDBInfo());

		getExtensionName();

		//System.out.println(extName);

		writeDataToFile();

		System.out.println("Update Info Extration Completed");

	}

	/**
	 * @throws IOException
	 */
	private static String getDBInfo() throws IOException
	{
		final File configFile = new File(fileName + "\\config\\local.properties");
		if (configFile.isFile())
		{
			BufferedReader br = null;
			try
			{
				br = new BufferedReader(new FileReader(configFile));
				String line = null;
				while ((line = br.readLine()) != null)
				{
					if (line.contains("db.driver") && !line.contains("#"))
					{
						//System.out.println(line);
						final String[] versionInfo = line.split("=");
						if (versionInfo.length > 1)
						{
							return versionInfo[1];
						}
					}

				}

			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (br != null)
				{
					br.close();
				}
			}
		}
		return null;
	}

	private static void printBuildNumberFile(final File[] fileList) throws IOException
	{
		for (final File f : fileList)
		{
			if (f.isFile() && f.getName().contains("build.number"))
			{
				//System.out.println(f.getName());
				BufferedReader br = null;
				try
				{
					br = new BufferedReader(new FileReader(f));
					String line = null;
					while ((line = br.readLine()) != null)
					{
						if (line.contains("version.api"))
						{
							//System.out.println(line);
							final String[] versionInfo = line.split("=");
							final String[] fileInfo = f.getName().split("\\.");

							if (versionInfo.length > 1 && fileInfo.length > 1)
							{
								versionMap.put(fileInfo[0], versionInfo[1]);
							}

							break;
						}

					}

				}
				catch (final Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					if (br != null)
					{
						br.close();
					}
				}

				//System.out.println(f.getName());
				break;
			}
			else
			{
				if (f.isDirectory())
				{
					printBuildNumberFile(f.listFiles());
				}
			}
		}



	}

	private static List<String> extName = new ArrayList<>();

	/**
	 * @throws IOException
	 */
	private static String getExtensionName() throws IOException
	{
		/*
		 * final ProcessBuilder builder = new ProcessBuilder("cmd.exe"); Process p = null; try { p = builder.start(); }
		 * catch (final IOException e) { System.out.println(e); }
		 * 
		 * //get stdin of shell final BufferedWriter p_stdin = new BufferedWriter(new
		 * OutputStreamWriter(p.getOutputStream()));
		 * 
		 * try { //single execution p_stdin.write("cd C:\\Ankit\\coronab2b_S7\\hybris\\bin\\platform"); p_stdin.newLine();
		 * p_stdin.write("setantenv.bat"); p_stdin.newLine(); p_stdin.write("ant extensionsxml"); p_stdin.newLine();
		 * p_stdin.flush(); } catch (final IOException e) { System.out.println(e); }
		 * 
		 * // finally close the shell by execution exit command try { p_stdin.write("exit"); p_stdin.newLine();
		 * p_stdin.flush(); } catch (final IOException e) { System.out.println(e); }
		 */


		final File configFile = new File(fileName + "\\config\\localextensions-generated.xml");
		if (configFile.isFile())
		{
			BufferedReader br = null;
			try
			{
				br = new BufferedReader(new FileReader(configFile));
				String line = null;
				while ((line = br.readLine()) != null)
				{
					if (line.contains("<extension "))
					{
						final int indexOfSlash = line.indexOf("'");
						final int indexOfquot = line.lastIndexOf("'");
						//System.out.println(line);
						//System.out.println(indexOfSlash);
						//System.out.println(indexOfquot);
						if (indexOfSlash > 0 && indexOfquot > 0)
						{
							final String tmp = line.substring(indexOfSlash, indexOfquot);
							final String[] filetmp = tmp.split("/");

							//System.out.println(tmp);
							if (filetmp.length > 0)
							{
								extName.add(filetmp[filetmp.length - 1]);
							}
						}

					}

				}

			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (br != null)
				{
					br.close();
				}
			}
		}
		return null;
	}

	private static void writeDataToFile() throws IOException
	{
		final File file = new File("upgradeInfo.properties");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (final Map.Entry<String, String> versionMapEntry : versionMap.entrySet())
		{
			bw.write(versionMapEntry.getKey() + "=" + versionMapEntry.getValue());
			bw.write("\n");
		}
		bw.write("extensions=");
		for (final String extName : extName)
		{
			bw.write(extName + ",");
		}
		bw.write("\n");
		bw.write("database=" + getDBInfo());
		bw.write("\n");
		bw.flush();


		bw.close();

	}
}
