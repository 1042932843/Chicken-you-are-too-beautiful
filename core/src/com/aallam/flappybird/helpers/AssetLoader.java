package com.aallam.flappybird.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by mouaad on 28/09/17.
 */

public class AssetLoader {

  private static final String TEXTURE_LOGO = "img/logo.png";
  private static final String TEXTURE_START_BUTTON = "img/start.png";
  private static final String TEXTURE_TUBE = "img/tube.png";
  private static final String TEXTURE_GROUND = "img/ground.png";
  private static final String TEXTURE_BACKGROUND_DAY = "img/background_day.png";
  private static final String TEXTURE_REGION_BIRD_FLY = "img/animation_bird.png";
  private static final String TEXTURE_GAMEOVER = "img/gameover.png";

  private static final String MUSIC_SOUND = "sound/music.mp3";
  private static final String SFX_WING_OGG = "sound/sfx_flap.ogg";
  private static final String SFX_DIE_OGG = "sound/sfx_die.ogg";
  private static final String SFX_POINT_OGG = "sound/sfx_point.ogg";
  private static final String SFX_HIT_OGG = "sound/sfx_hit.ogg";

  public static final int ANIMATION_BIRD_FRAME_COUNT = 3;
  public static Texture TEXTURE_BIRD_ANIMATION;

  public static TextureRegion[] BIRD;
  public static Animation<TextureRegion> BIRD_ANIMATION;
  public static Texture LOGO, START, TUBE, GROUND, BACKGROUND_DAY, GAMEOVER;
  public static Music MUSIC;
  public static Sound SFX_WING, SFX_DIE, SFX_HIT, SFX_POINT;
  public static BitmapFont FONT, SHADOW;
  public static Preferences PREFS;

  public static void load() {
    loadPrefs();
    loadTextures();
    loadSounds();
    loadFonts();
  }

  private static void loadPrefs() {
    PREFS = Gdx.app.getPreferences("FlappyBird");
    if (!PREFS.contains("highScore")) {
      PREFS.putInteger("highScore", 0);
    }
  }

  private static void loadTextures() {
    LOGO = new Texture(Gdx.files.internal(TEXTURE_LOGO));
    LOGO.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    START = new Texture(Gdx.files.internal(TEXTURE_START_BUTTON));
    TUBE = new Texture(Gdx.files.internal(TEXTURE_TUBE));
    GROUND = new Texture(Gdx.files.internal(TEXTURE_GROUND));
    BACKGROUND_DAY = new Texture(Gdx.files.internal(TEXTURE_BACKGROUND_DAY));
    GAMEOVER = new Texture(Gdx.files.internal(TEXTURE_GAMEOVER));

    BIRD = new TextureRegion[ANIMATION_BIRD_FRAME_COUNT];
    TEXTURE_BIRD_ANIMATION = new Texture(TEXTURE_REGION_BIRD_FLY);
    TextureRegion textureRegion = new TextureRegion(TEXTURE_BIRD_ANIMATION);
    int frameWidth = TEXTURE_BIRD_ANIMATION.getWidth() / ANIMATION_BIRD_FRAME_COUNT;
    // Create frames from the every single texture of the animation
    for (int i = 0; i < ANIMATION_BIRD_FRAME_COUNT; i++) {
      BIRD[i] = new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight());
    }
    BIRD_ANIMATION = new Animation<TextureRegion>(0.06f, BIRD);
    BIRD_ANIMATION.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
  }

  private static void loadSounds() {
    SFX_POINT = Gdx.audio.newSound(Gdx.files.internal(SFX_POINT_OGG));
    SFX_WING = Gdx.audio.newSound(Gdx.files.internal(SFX_WING_OGG));
    SFX_DIE = Gdx.audio.newSound(Gdx.files.internal(SFX_DIE_OGG));
    SFX_HIT = Gdx.audio.newSound(Gdx.files.internal(SFX_HIT_OGG));
    MUSIC = Gdx.audio.newMusic(Gdx.files.internal(MUSIC_SOUND));
    MUSIC.setLooping(true);
  }

  private static void loadFonts() {
    FONT = new BitmapFont(Gdx.files.internal("text/text.fnt"));
    FONT.getData().setScale(.25f, .25f);
    SHADOW = new BitmapFont(Gdx.files.internal("text/shadow.fnt"));
    SHADOW.getData().setScale(.25f, .25f);
  }

  public static void setHighScore(int score) {
    PREFS.putInteger("highScore", score);
    PREFS.flush();
  }

  public static int getHighScore() {
    return PREFS.getInteger("highScore");
  }

  public static void dispose() {
    LOGO.dispose();
    TEXTURE_BIRD_ANIMATION.dispose();
    START.dispose();
    TUBE.dispose();
    GROUND.dispose();
    BACKGROUND_DAY.dispose();
    MUSIC.dispose();
    SFX_WING.dispose();
    SFX_DIE.dispose();
    SFX_POINT.dispose();
    SFX_HIT.dispose();
    FONT.dispose();
    SHADOW.dispose();
    GAMEOVER.dispose();
  }
}
