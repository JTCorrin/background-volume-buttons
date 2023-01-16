export interface BackgroundVolumeButtonListenerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
