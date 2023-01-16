import { WebPlugin } from '@capacitor/core';

import type { BackgroundVolumeButtonListenerPlugin } from './definitions';

export class BackgroundVolumeButtonListenerWeb extends WebPlugin implements BackgroundVolumeButtonListenerPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
