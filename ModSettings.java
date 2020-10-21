package packagename.gui.hud;

import java.io.File;

import packagename.FileManager;
import packagename.gui.Cosmetics;

public abstract class ModSettings {
	
	protected Cosmetics status;
	
	public ModSettings() {
		status = loadStatusFromFile();
	}

	public Cosmetics load() {
		return status;
	}
	
	public void save(Cosmetics status) {
		this.status = status;
		savePositionToFile();
	}
	
	private File getFolder() {
		File folder = new File(FileManager.getModsDirectory(), this.getClass().getSimpleName());
		folder.mkdirs();
		return folder;
	}
	
	private void savePositionToFile() {
		FileManager.writeJsonToFile(new File(getFolder(), "status.json"), status);
	}
	
	private Cosmetics loadStatusFromFile() {
		
		Cosmetics loaded = FileManager.readFromJson(new File(getFolder(), "status.json"), Cosmetics .class);
		
		if(loaded == null) {
			loaded = Cosmetics .dontknowhowtonamethis(false, false, false);
			this.status = loaded;
			savePositionToFile();
		}
		
		return loaded;
	}
	
}
